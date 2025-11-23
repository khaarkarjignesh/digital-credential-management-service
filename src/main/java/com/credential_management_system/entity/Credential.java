package com.credential_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@NoArgsConstructor
public class Credential {

	@Id
	@GeneratedValue
	private Long id;

	private Long userId;

	@ManyToOne
	private CredentialTemplate template;

	@ElementCollection
	private Map<String, String> data = new HashMap<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public CredentialTemplate getTemplate() {
		return template;
	}

	public void setTemplate(CredentialTemplate template) {
		this.template = template;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

}
