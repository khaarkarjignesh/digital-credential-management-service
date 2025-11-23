package com.credential_management_system.exception;

public class TemplateAlreadyExistsException extends RuntimeException {
	public TemplateAlreadyExistsException(String templateName) {
		super("Template already exists: " + templateName);
	}
}