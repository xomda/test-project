import java.io.IOException;
import java.nio.file.Paths;

import org.xomda.core.module.template.XOmdaCodeTemplate;
import org.xomda.core.module.util.PojoWriter;
import org.xomda.model.Entity;
import org.xomda.model.Package;
import org.xomda.template.TemplateContext;
import org.xomda.template.TemplateUtils;

public class TestModelTemplate extends XOmdaCodeTemplate {
	@Override
	public void generate(final Package pkg, final TemplateContext context) throws IOException {
		String newPath = Paths.get(context.outDir(), "src", "generated", "java").toString();
		TemplateContext newContext = new TemplateContext(newPath, context.getParseResults());

		getLogger().info("Generating multi-model (" + pkg.getName() + ")");
		super.generate(pkg, newContext);
	}

	@Override
	public void generate(final Entity entity, final TemplateContext context) throws IOException {
		String javaInterface = TemplateUtils.getJavaInterfaceName(entity);
		String javaClass = TemplateUtils.getJavaBeanName(entity);
		PojoWriter
				.createInterface(context.outDir(), javaInterface)
				.write(entity);
		PojoWriter
				.create(context.outDir(), javaClass)
				.withImplements(javaInterface)
				.write(entity);
	}

}
