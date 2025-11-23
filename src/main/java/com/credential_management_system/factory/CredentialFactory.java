package com.credential_management_system.factory;

import com.credential_management_system.entity.Credential;
import com.credential_management_system.entity.CredentialTemplate;
import com.credential_management_system.exception.TemplateNotFoundException;
import com.credential_management_system.repository.TemplateRepository;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CredentialFactory {

	private final TemplateRepository repository;

	public CredentialFactory(TemplateRepository repository) {
		this.repository = repository;
	}

	public Credential create(Long userId, String templateName, Map<String, String> data) {
		CredentialTemplate t = repository.findByTemplateName(templateName);
		if (t == null) {
			throw new TemplateNotFoundException(templateName);
		}

		Credential c = new Credential();
		c.setUserId(userId);
		c.setTemplate(t);
		c.setData(data);
		return c;
	}
}