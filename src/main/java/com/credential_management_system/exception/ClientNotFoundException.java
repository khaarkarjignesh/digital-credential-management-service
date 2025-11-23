package com.credential_management_system.exception;

public class ClientNotFoundException extends RuntimeException {
	public ClientNotFoundException(Long clientId) {
		super("User not found with ID: " + clientId);
	}
}
