package com.example.template;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.xomda.model.Package;
import org.xomda.template.Template;
import org.xomda.template.TemplateContext;

public class MustacheTemplate implements Template<Package> {
	@Override
	public void generate(final Package aPackage, final TemplateContext templateContext) throws IOException {
		MustacheFactory mf = new DefaultMustacheFactory();
		Path out = Paths.get(templateContext.cwd(), "build", "test-mustache", "output.json");
		Files.createDirectories(out.getParent());

		try (
				FileReader fr = new FileReader(templateContext.cwd() + "/src/xomda/java/template.mustache");
				Writer pw = new FileWriter(out.toFile())
		) {
			Mustache mustache = mf.compile(fr, "mustache template");
			mustache.execute(pw, aPackage);
		}
	}
}
