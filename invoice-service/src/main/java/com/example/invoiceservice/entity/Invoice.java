package com.example.invoiceservice.entity;

import com.example.invoiceservice.entity.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

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

    private String name;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    private String purpose;

}
