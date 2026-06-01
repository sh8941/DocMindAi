package com.haider.DocMindAi.controller;

import com.haider.DocMindAi.entity.DocumentEntity;
import com.haider.DocMindAi.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;


    @PostMapping("/upload")
    public ResponseEntity<DocumentEntity> upload(
            @RequestParam String title,
            @RequestParam MultipartFile file
    ) throws Exception {

        return ResponseEntity.ok(
                documentService.uploadDocument(title, file)
        );
    }
}
