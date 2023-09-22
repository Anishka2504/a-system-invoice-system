package com.example.invoiceservice.converter;

import com.example.invoiceservice.converter.uses.DateTimeMapper;
import com.example.invoiceservice.dto.UploadedFileDto;
import com.example.invoiceservice.entity.UploadedFile;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component("uploadedFileConverter")
@Mapper(componentModel = "spring", uses = DateTimeMapper.class)
public interface UploadedFileConverter {

    UploadedFile toEntity(UploadedFileDto dto);

    UploadedFileDto toDto(UploadedFile uploadedFile);
}

