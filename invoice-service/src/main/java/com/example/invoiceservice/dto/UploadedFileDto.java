package com.example.invoiceservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UploadedFileDto {

    private Long id;
    private String name;
    private String dateUpload;
    private String dateModified;
    private String status;

}
