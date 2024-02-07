package com.example.template;

public class TemplateConfig {

	private String filename;
	private boolean trim = true;

	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * The target filename where to write to
	 */
	public String getFilename() {
		return filename;
	}

	public void setTrim(boolean trim) {
		this.trim = trim;
	}

	/**
	 * Trim the output before writing
	 */
	public boolean getTrim() {
		return trim;
	}

}
