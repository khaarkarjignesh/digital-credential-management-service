package com.credential_management_system.strategy;

import com.credential_management_system.entity.Credential;
import com.credential_management_system.service.CredentialService;

import org.springframework.stereotype.Component;

@Component
public class AadhaarValidator implements CredentialVerify {
	@Override
	public boolean supports(String templateName) {
		return "Aadhaar".equalsIgnoreCase(templateName);
	}

	@Override
	public CredentialService.VerifyResult validate(Credential credential) {
		String number = credential.getData().get("aadhaarNumber");
		if (number == null || !number.matches("\\d{12}")) {
			return new CredentialService.VerifyResult(false, "Aadhaar must be 12 digits");
		}
		return new CredentialService.VerifyResult(true, "Aadhaar is valid");
	}
}
