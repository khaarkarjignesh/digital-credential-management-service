package com.credential_management_system.strategy;

import com.credential_management_system.entity.Credential;
import com.credential_management_system.service.CredentialService;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;

@Component
public class DrivingLicenseValidator implements CredentialVerify {

	@Override
	public boolean supports(String t) {
		return "drivinglicense".equalsIgnoreCase(t);
	}

	@Override
	public CredentialService.VerifyResult validate(Credential credential) {
		Map<String, String> data = credential.getData();
		String validTill = data.get("validTill");

		if (validTill == null) {
			return new CredentialService.VerifyResult(false, "Missing validTill date");
		}
		try {
			if (LocalDate.parse(validTill).isBefore(LocalDate.now())) {
				return new CredentialService.VerifyResult(false, "Driving license has expired");
			}
		} catch (Exception e) {
			return new CredentialService.VerifyResult(false, "Invalid validTill format");
		}
		return new CredentialService.VerifyResult(true, "Driving license is valid");
	}
}
