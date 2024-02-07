import org.xomda.core.template.Template;
import org.xomda.core.template.TemplateContext;

public class TestTemplate implements Template<Object> {

	@Override
	public void generate(Object o, TemplateContext ctx) {
		System.out.println("Template 1 works! (" + o.getClass() + ") [" + ctx.outDir() + "]");
		System.out.println();
	}
}
