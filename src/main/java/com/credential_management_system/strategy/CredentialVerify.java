package com.credential_management_system.strategy;

import com.credential_management_system.entity.Credential;
import com.credential_management_system.service.CredentialService;

public interface CredentialVerify {
	boolean supports(String templateName);

	CredentialService.VerifyResult validate(Credential credential);
}
