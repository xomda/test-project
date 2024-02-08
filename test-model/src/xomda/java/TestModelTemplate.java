import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringJoiner;

import org.xomda.core.module.template.PackageTemplate;
import org.xomda.model.Attribute;
import org.xomda.model.Entity;
import org.xomda.template.TemplateContext;

public class TestModelTemplate extends PackageTemplate {
	@Override
	public void generate(org.xomda.model.Package pkg, TemplateContext context) throws IOException {
		getLogger().error("Generating package {} to {}", pkg.getName(), context.outDir());

		Path targetDir = Files.createDirectories(Paths.get(context.outDir(), "build", "test-model-data", "templateTest"));
		Path targetFile = targetDir.resolve("test-data.txt");
		try (
				OutputStream os = new FileOutputStream(targetFile.toFile());
				BufferedOutputStream bos = new BufferedOutputStream(os);
				PrintWriter out = new PrintWriter(bos)
		) {
			pkg.getEntityList().forEach((Entity entity) -> {
				StringJoiner sj = new StringJoiner("|")
						.add(pkg.getName())
						.add(entity.getName());
				entity.getAttributeList().forEach((Attribute attribute) -> {
					sj.add(attribute.getName() + "[" + attribute.getType() + "]");
				});
				out.println(sj);
			});

		}
	}

}
