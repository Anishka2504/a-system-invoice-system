package com.example.invoiceservice.dto;

import com.example.invoiceservice.entity.Company;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class InvoiceDto {

    private Long id;
    private Company companyId;
    private String name;
    private String dateCreation;
    private String datePayment;
    private double amount;
    private String currency;
    private String purpose;

}
