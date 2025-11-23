package com.credential_management_system.repository;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.credential_management_system.entity.Credential;

@Repository
@Hidden
public interface CredentialRepository extends JpaRepository<Credential, Long> {
	Page<Credential> findByUserId(Long userId, Pageable p);
}
