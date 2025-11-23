package com.credential_management_system.strategy;

import com.credential_management_system.entity.Credential;
import com.credential_management_system.service.CredentialService;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class PassportValidator implements CredentialVerify {

	@Override
	public boolean supports(String templateName) {
		return "passport".equalsIgnoreCase(templateName);
	}

	@Override
	public CredentialService.VerifyResult validate(Credential credential) {
		Map<String, String> data = credential.getData();

		if (!data.containsKey("expiryDate")) {
			return new CredentialService.VerifyResult(false, "Missing expiryDate field");
		}

		try {
			LocalDate expiry = LocalDate.parse(data.get("expiryDate"));
			if (expiry.isBefore(LocalDate.now())) {
				return new CredentialService.VerifyResult(false, "Passport has expired");
			}
		} catch (Exception e) {
			return new CredentialService.VerifyResult(false, "Invalid expiry date format (use YYYY-MM-DD)");
		}

		return new CredentialService.VerifyResult(true, "Passport is valid and not expired");
	}

}
