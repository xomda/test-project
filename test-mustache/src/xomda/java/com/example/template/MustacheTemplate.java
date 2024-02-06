package com.example.template;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.xomda.core.template.Template;
import org.xomda.core.template.TemplateContext;
import org.xomda.model.Package;

public class MustacheTemplate implements Template<Package> {
    @Override
    public void generate(final Package aPackage, final TemplateContext templateContext) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        try (
            FileReader fr = new FileReader(templateContext.outDir() + "/src/xomda/java/template.mustache");
            PrintWriter pw = new PrintWriter(System.out);
        ) {
            pw.write("MUSTACHE: \n\n");

            Mustache mustache = mf.compile(fr, "o");
            mustache.execute(pw, aPackage);
        }
    }
}
