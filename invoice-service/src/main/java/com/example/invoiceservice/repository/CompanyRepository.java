package com.example.invoiceservice.repository;

import com.example.invoiceservice.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findCompanyByExternalId(Long externalId);
}
