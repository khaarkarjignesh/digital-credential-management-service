package com.credential_management_system.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credential_management_system.entity.CredentialTemplate;

@Repository
@Hidden
public interface TemplateRepository extends JpaRepository<CredentialTemplate, Long> {
	CredentialTemplate findByTemplateName(String name);

	boolean existsByTemplateName(String name);
}