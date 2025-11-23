package com.credential_management_system.exception;

public class TemplateNotFoundException extends RuntimeException {
	public TemplateNotFoundException(String templateName) {
		super("Template not found: " + templateName);
	}
}