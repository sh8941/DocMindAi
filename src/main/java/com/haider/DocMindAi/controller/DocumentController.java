package com.haider.DocMindAi.controller;

import com.haider.DocMindAi.entity.DocumentEntity;
import com.haider.DocMindAi.repo.DocumentRepo;
import com.haider.DocMindAi.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;
    @Autowired
    private DocumentRepo documentRepo;


    @PostMapping
    public ResponseEntity<DocumentEntity> upload(
            @RequestParam String title,
            @RequestParam MultipartFile file
    ) throws Exception {

        return ResponseEntity.ok(
                documentService.uploadDocument(title, file)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getDocumentById(@PathVariable Long id) {
        Resource resource = documentService.downloadDocument(id);
        DocumentEntity documentEntity = documentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Document not found"));
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\""
                                + documentEntity.getOriginalFileName()
                                + "\""
                )
                .contentType(
                        MediaType.parseMediaType(
                                documentEntity.getFileType()
                        )
                )
                .body(resource);
    }
}
