package com.example.invoiceservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long externalId;
    @Column(name = "name")
    private String companyName;

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        Company company = (Company) o;
        if (this.hashCode() != company.hashCode()) {
            return false;
        }

        if (!Objects.equals(this.getId(), company.getId())
                || !Objects.equals(this.getExternalId(), company.getExternalId())) {
            return false;
        }
        return this.getCompanyName().equals(company.getCompanyName());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getId());
        result = 31 * result + Objects.hash(getExternalId());
        result = 31 * result + Objects.hash(getCompanyName());
        return result;
    }
}
