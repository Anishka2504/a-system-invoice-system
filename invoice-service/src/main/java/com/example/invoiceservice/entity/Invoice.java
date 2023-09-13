package com.example.invoiceservice.entity;

import com.example.invoiceservice.entity.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @PrimaryKeyJoinColumn(name = "company_id")
    @Cascade(value = org.hibernate.annotations.CascadeType.PERSIST)
    private Company companyId;

    private String name;

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "pay_before")
    private LocalDateTime datePayment;

    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;

    private String purpose;

}
