package com.example.invoiceservice.repository;

import com.example.invoiceservice.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findCompanyByExternalId(Long externalId);
}
