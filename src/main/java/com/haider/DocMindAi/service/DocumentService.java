package com.haider.DocMindAi.service;

import com.haider.DocMindAi.entity.DocumentEntity;
import com.haider.DocMindAi.repo.DocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

        return documentRepo.save(document);


    }

}
