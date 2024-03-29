package com.example.template;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import gg.jte.CodeResolver;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.TemplateOutput;
import gg.jte.output.StringOutput;
import gg.jte.resolve.DirectoryCodeResolver;
import org.xomda.model.Package;
import org.xomda.template.Template;
import org.xomda.template.TemplateContext;

public class JteTemplate implements Template<Package> {
	@Override
	public void generate(final Package aPackage, final TemplateContext templateContext) throws IOException {
		TemplateConfig config = new TemplateConfig();

		Map<String, Object> state = new ConcurrentHashMap<>();
		state.put("pkg", aPackage);
		state.put("config", config);
		state.put("out", "");

		//
		Path outPath = Path.of(templateContext.cwd());
		Path templatePath = outPath.resolve(Paths.get("src", "xomda", "java"));
		Path classPath = outPath.resolve(Paths.get("build", "jte-classes"));

		CodeResolver codeResolver = new DirectoryCodeResolver(templatePath);
		TemplateEngine templateEngine = TemplateEngine.create(
				// resolves where the JTE templates are
				codeResolver,
				// we need to specify where the templates will be compiled,
				// because JTE does some nasty assumptions there
				classPath,
				// Plain or HTML? Plain of course.
				ContentType.Plain,
				// without classloader, it will fail
				getClass().getClassLoader()
		);

		TemplateOutput output = new StringOutput();
		templateEngine.render("example.jte", state, output);

		Path outputJson = outPath.resolve(Paths.get("build", "test-jte", config.getFilename()));
		Files.createDirectories(outputJson.getParent());

		try (FileWriter fw = new FileWriter(outputJson.toFile())) {
			fw.write((config.getTrim() ? output.toString().trim() : output).toString());
		}
	}
}
