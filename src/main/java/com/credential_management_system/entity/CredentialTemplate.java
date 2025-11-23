package com.credential_management_system.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class CredentialTemplate {

	@Id
	@GeneratedValue
	private Long id;

	private String templateName;

	@ElementCollection
	private List<String> fields = new ArrayList<>();

	public CredentialTemplate() {
	}

	public Long getId() {
		return id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public List<String> getFields() {
		return fields;
	}

	public static class Builder {
		private String name;
		private List<String> fields = new ArrayList<>();

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder fields(List<String> f) {
			this.fields.addAll(f);
			return this;
		}

		public CredentialTemplate build() {
			CredentialTemplate t = new CredentialTemplate();
			t.templateName = this.name;
			t.fields = this.fields;
			return t;
		}
	}
}