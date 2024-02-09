import java.io.IOException;
import java.nio.file.Paths;

import org.xomda.core.module.template.XOmdaCodeTemplate;
import org.xomda.model.Package;
import org.xomda.template.TemplateContext;

public class TestModelTemplate extends XOmdaCodeTemplate {
	@Override
	public void generate(final Package pkg, final TemplateContext context) throws IOException {
		String newPath = Paths.get(context.outDir(), "src", "generated", "java").toString();
		TemplateContext newContext = new TemplateContext(newPath, context.getParseResults());

		getLogger().info("Generating multi-model (" + pkg.getName() + ") to: " + newContext.outDir());
		super.generate(pkg, newContext);
	}
}
