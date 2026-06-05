package com.haider.DocMindAi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.IdGeneratorType;

@Data
@Entity
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String originalFileName;
    private String fileType;
    private String filePath;
    private boolean active;
    @Column(columnDefinition = "TEXT")
    private String extractedText;
}
