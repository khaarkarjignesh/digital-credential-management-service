package com.credential_management_system.service.impl;

import com.credential_management_system.entity.Credential;
import com.credential_management_system.entity.CredentialTemplate;
import com.credential_management_system.exception.ClientNotFoundException;
import com.credential_management_system.exception.TemplateAlreadyExistsException;
import com.credential_management_system.exception.TemplateNotFoundException;
import com.credential_management_system.factory.CredentialFactory;
import com.credential_management_system.repository.CredentialRepository;
import com.credential_management_system.repository.TemplateRepository;
import com.credential_management_system.service.CredentialService;
import com.credential_management_system.strategy.CredentialVerify;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;

@Service
public class CredentialServiceImpl implements CredentialService {

	private final TemplateRepository tRepo;
	private final CredentialRepository cRepo;
	private final CredentialFactory factory;
	private final List<CredentialVerify> validators;

	private static final Logger log = LoggerFactory.getLogger(CredentialServiceImpl.class);

	public CredentialServiceImpl(TemplateRepository tRepo, CredentialRepository cRepo, CredentialFactory factory,
			List<CredentialVerify> validators) {
		this.tRepo = tRepo;
		this.cRepo = cRepo;
		this.factory = factory;
		this.validators = validators;
	}

	public Long createTemplate(String name, List<String> fields) {
		log.info("Creating template: {}", name); // Clean & safe
		log.debug("Fields received: {}", fields);

		if (tRepo.existsByTemplateName(name)) {
			log.warn("Template already exists: {}", name);
			throw new TemplateAlreadyExistsException("Template already exists!" + name);
		}

		CredentialTemplate t = new CredentialTemplate.Builder().name(name).fields(fields).build();

		CredentialTemplate saved = tRepo.save(t);
		log.info("Template created successfully with ID: {}", saved.getId());
		return saved.getId();
	}

	public Long issueCredential(Long userId, String templateName, Map<String, String> data) {
		log.info("Issuing credential for userId: {}, template: {}", userId, templateName);
		Credential c = factory.create(userId, templateName, data);
		Credential saved = cRepo.save(c);
		log.info("Credential issued successfully! ID: {}", saved.getId());
		log.debug("Credential data: {}", saved.getData());
		return saved.getId();
	}

	public VerifyResult verifyCredential(Long credentialId) {
		log.info("Verifying credential with ID: {}", credentialId);
		Credential credential = cRepo.findById(credentialId).orElseThrow(() -> {
			log.warn("Credential not found with ID: {}", credentialId);
			return new ClientNotFoundException(credentialId);
		});

		String templateName = credential.getTemplate().getTemplateName();
		CredentialVerify validator = null;
		for (CredentialVerify v : validators) {
			if (v.supports(templateName)) {
				validator = v;
				break;
			}
		}
		if (validator == null) {
			throw new TemplateNotFoundException("No validator for: " + templateName);
		}

		VerifyResult result = validator.validate(credential);
		log.info("Verification result: {}", result);

		return result;
	}

	public List<Credential> getUserCredentials(Long userId, int page, int size) {
		log.info("Fetching credentials for userId: {}, page: {}, size: {}", userId, page, size);
		Page<Credential> resultPage = cRepo.findByUserId(userId, PageRequest.of(page, size));
		if (resultPage.isEmpty()) {
			throw new ClientNotFoundException(userId);
		}
		log.info("Returning {} credentials", resultPage.getContent().size());
		return cRepo.findByUserId(userId, PageRequest.of(page, size)).getContent();
	}

}
