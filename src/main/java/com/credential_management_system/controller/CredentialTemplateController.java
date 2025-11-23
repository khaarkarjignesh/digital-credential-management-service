package com.credential_management_system.controller;

import com.credential_management_system.entity.Credential;
import com.credential_management_system.exception.ClientNotFoundException;
import com.credential_management_system.service.CredentialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CredentialTemplateController {

	@Autowired
	private CredentialService service;

	@PostMapping("/credential-templates")
	public Map<String, Object> createTemplate(@RequestBody Map<String, Object> body) {
		String name = (String) body.get("templateName");
		List<String> fields = (List<String>) body.get("fields");

		Long id = service.createTemplate(name, fields);

		return Map.of("templateId", id);
	}

	@PostMapping("/credentials")
	public Map<String, Object> issue(@RequestBody Map<String, Object> body) {
		Long userId = Long.valueOf(body.get("userId").toString());
		String tName = (String) body.get("templateName");
		Map<String, String> data = (Map<String, String>) body.get("data");

		Long id = service.issueCredential(userId, tName, data);

		return Map.of("credentialId", id);
	}

	@PostMapping("/credentials/verify")
	public ResponseEntity<Map<String, Object>> verifyCredential(@RequestBody Map<String, Long> body) {

		Long credentialId = body.get("credentialId");
		if (credentialId == null || credentialId <= 0) {
			throw new ClientNotFoundException(credentialId);
		}

		CredentialService.VerifyResult result = service.verifyCredential(credentialId);

		Map<String, Object> response = Map.of("verified", result.verified(), "message", result.message());

		return ResponseEntity.ok(response); // Pure JSON, no wrapper
	}

	@GetMapping("/users/{userId}/credentials")
	public List<Credential> get(@PathVariable Long userId, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		return service.getUserCredentials(userId, page, size);
	}
}