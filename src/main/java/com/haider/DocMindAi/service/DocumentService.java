package com.haider.DocMindAi.service;

import com.haider.DocMindAi.entity.DocumentEntity;
import com.haider.DocMindAi.repo.DocumentRepo;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepo documentRepo;
    @Value("${file.upload-dir}")
    private String uploadDir;

    public DocumentEntity uploadDocument(String title, MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = System.currentTimeMillis()
                + "_"
                + file.getOriginalFilename();

        Path filePath = uploadPath.resolve(fileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        DocumentEntity document = new DocumentEntity();

        document.setTitle(title);
        document.setOriginalFileName(file.getOriginalFilename());
        document.setFileType(file.getContentType());
        document.setFilePath(filePath.toString());
        document.setActive(true);


        File tempFile = File.createTempFile(fileName, ".pdf");
        file.transferTo(tempFile); // multipart file to file
        String text = extractTextFromDocument(tempFile);
        document.setExtractedText(text);



        return documentRepo.save(document);


    }

    public Resource downloadDocument(Long id) {
        DocumentEntity documentEntity = documentRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Document not found"));
        Path filePath = Paths.get(documentEntity.getFilePath());

        try{
            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new RuntimeException("Document not found");
            }
            return resource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String extractTextFromDocument(File file) throws IOException {
        try (PDDocument document = Loader.loadPDF(file)) {
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            return  pdfTextStripper.getText(document);
        }
    }

}
