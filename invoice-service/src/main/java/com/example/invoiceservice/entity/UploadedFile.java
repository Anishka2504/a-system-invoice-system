package com.example.invoiceservice.entity;

import com.example.invoiceservice.entity.enums.FileStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "uploaded_file")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UploadedFile {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;

    @Column(name = "date_upload")
    private LocalDateTime dateUpload;

    @Column(name = "date_modified")
    private LocalDateTime dateModified;

    @Enumerated(value = EnumType.STRING)
    private FileStatus status;
}
