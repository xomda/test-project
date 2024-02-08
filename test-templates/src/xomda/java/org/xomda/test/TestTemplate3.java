package org.xomda.test;

import java.io.IOException;

import org.xomda.core.module.template.PackageTemplate;
import org.xomda.model.Entity;
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
		getLogger().warn("Template 3: Generating package: {}", entity.getName());
	}
}
