package com.example.invoiceservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Value;
import org.yaml.snakeyaml.events.Event;

import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @Cascade(value = org.hibernate.annotations.CascadeType.PERSIST)
    private Company companyId;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    private Double amount;

    private String currency;

    private String purpose;

}
