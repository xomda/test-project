import java.io.IOException;
import java.nio.file.Paths;

import org.xomda.core.module.template.PackageTemplate;
import org.xomda.core.module.util.EnumWriter;
import org.xomda.core.module.util.PojoWriter;
import org.xomda.model.Entity;
import org.xomda.model.Enum;
import org.xomda.model.Package;
import org.xomda.template.TemplateContext;
import org.xomda.template.TemplateUtils;

public class TestModelTemplate extends PackageTemplate {
	@Override
	public void generate(final Package pkg, final TemplateContext context) throws IOException {
		String newPath = Paths.get(context.cwd(), "src", "generated", "java").toString();
		TemplateContext newContext = new TemplateContext(newPath, context.getParseResults());

		getLogger().info("Generating multi-model (" + pkg.getName() + ")");
		super.generate(pkg, newContext);
	}

	@Override
	public void generate(final Entity entity, final TemplateContext context) throws IOException {
		String javaInterface = TemplateUtils.getJavaInterfaceName(entity);
		String javaClass = TemplateUtils.getJavaBeanName(entity);
		PojoWriter
				.createInterface(context.cwd(), javaInterface)
				.write(entity);
		PojoWriter
				.create(context.cwd(), javaClass)
				.withImplements(javaInterface)
				.write(entity);
	}

	@Override
	public void generate(final Enum enm, final TemplateContext context) throws IOException {
		EnumWriter.create(context.cwd(), TemplateUtils.getJavaEnumName(enm))
				.write(enm);
	}
}
