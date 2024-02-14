package com.example;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
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
		root.put("setConfig", new ConfigSetter(root));

		Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
		Path templatePath = Paths.get(templateContext.cwd(), "src", "xomda", "java");
		configuration.setDirectoryForTemplateLoading(templatePath.toFile());
		configuration.setDefaultEncoding("UTF-8");

		freemarker.template.Template template = configuration.getTemplate("example.ftl");

		try (
				Writer out = new OutputStreamWriter(System.out);
		) {
			template.process(root, out);

			System.out.println();
			System.out.println("Filename: " + root.get("filename"));

		} catch (TemplateException e) {
			throw new IOException(e);
		}
	}
}
