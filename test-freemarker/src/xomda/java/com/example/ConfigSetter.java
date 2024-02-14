package com.example;

import java.util.List;
import java.util.Map;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class ConfigSetter implements TemplateMethodModelEx {

	Map<String, Object> config;

	public ConfigSetter(Map<String, Object> config) {
		this.config = config;
	}

	public TemplateModel exec(List args) throws TemplateModelException {
		if (args.size() != 2) {
			throw new TemplateModelException("Wrong arguments");
		}
		config.put(args.get(0).toString(), args.get(1));
		return TemplateModel.NOTHING;
	}
}