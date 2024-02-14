package org.xomda.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.xomda.core.module.template.PackageTemplate;
import org.xomda.model.Entity;
import org.xomda.shared.util.StringUtils;
import org.xomda.template.TemplateContext;

public class TestTemplate3 extends PackageTemplate {

	@Override
	public void generate(org.xomda.model.Package pkg, TemplateContext context) throws IOException {
		super.generate(pkg, context);
		getLogger().warn("");
	}

	@Override
	public void generate(Entity entity, TemplateContext context) throws IOException {
		super.generate(entity, context);
		String filename = StringUtils.toCamelCase(entity.getName()) + ".json";
		Path target = Paths.get(context.cwd(), "build", "test-templates", filename);
		Files.createDirectories(target.getParent());

		try (
				FileWriter writer = new FileWriter(target.toFile());
				PrintWriter pw = new PrintWriter(writer);
		) {
			pw.println("{");
			pw.println("  \"name\":" + "\"" + entity.getName() + "\"");
			pw.println("}");
		}
	}
}
