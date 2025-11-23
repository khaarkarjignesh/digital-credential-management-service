package com.credential_management_system.service;

import java.util.List;
import java.util.Map;

import com.credential_management_system.entity.Credential;

public interface CredentialService {
	Long createTemplate(String name, java.util.List<String> fields);

	public Long issueCredential(Long userId, String templateName, Map<String, String> data);

	public List<Credential> getUserCredentials(Long userId, int page, int size);

	public VerifyResult verifyCredential(Long credentialId);

	record VerifyResult(boolean verified, String message) {
	}
}