package org.xomda.test;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.xomda.core.template.Template;
import org.xomda.core.template.TemplateContext;

public class TestTemplate2 implements Template<org.xomda.model.Package> {
    private static final Logger logger = LogManager.getLogger(TestTemplate2.class);

    static {
        Configurator.setAllLevels("", Level.ALL);
    }

    @Override
    public void generate(org.xomda.model.Package pkg, TemplateContext context) {
        logger.info("Template 2 works with custom dependencies!");
        logger.info("Root Package name: " + pkg.getName());
    }
}
