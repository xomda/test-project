import org.xomda.template.Template;
import org.xomda.template.TemplateContext;

public class TestTemplate implements Template<Object> {

	@Override
	public void generate(final Object o, final TemplateContext ctx) {
		System.out.println("Template 1 works! (" + o.getClass() + ") [" + ctx.cwd() + "]");
		System.out.println();
	}

}
