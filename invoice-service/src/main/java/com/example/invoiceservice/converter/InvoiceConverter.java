package com.example.invoiceservice.converter;

import com.example.invoiceservice.converter.uses.DateTimeMapper;
import com.example.invoiceservice.dto.InvoiceDto;
import com.example.invoiceservice.entity.Invoice;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", uses = {DateTimeMapper.class})
public interface InvoiceConverter {

    InvoiceDto toDto(Invoice invoice);

    Invoice toEntity(InvoiceDto dto);
}
