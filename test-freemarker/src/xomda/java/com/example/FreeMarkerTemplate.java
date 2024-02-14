package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateException;
import org.xomda.model.Package;
import org.xomda.template.Template;
import org.xomda.template.TemplateContext;

public class FreeMarkerTemplate implements Template<Package> {
	@Override
	public void generate(final Package aPackage, final TemplateContext templateContext) throws IOException {
		Map<String, Object> root = new HashMap<>();
		root.put("pkg", aPackage);
		root.put("filename", "");

		// inject some functionality
		root.put(ConfigSetter.METHOD_NAME, new ConfigSetter(root));

		Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
		Path templatePath = Paths.get(templateContext.cwd(), "src", "xomda", "java");
		configuration.setDirectoryForTemplateLoading(templatePath.toFile());
		configuration.setDefaultEncoding("UTF-8");

		freemarker.template.Template template = configuration.getTemplate("example.ftl");

		StringBuilder sb = new StringBuilder();
		try (Writer out = new StringWriter();) {
			template.process(root, out);
			sb.append(out);
		} catch (TemplateException e) {
			throw new IOException(e);
		}

		if (!(root.get("filename") instanceof SimpleScalar filename) || filename.getAsString().isBlank()) {
			throw new IOException("No output filename specified!");
		}

		Path buildPath = Paths.get(templateContext.cwd(), "build", "test-freemarker", filename.getAsString());
		Files.createDirectories(buildPath.getParent());
		try (Writer out = new FileWriter(buildPath.toFile());) {
			out.write(sb.toString());
		}
	}
}
