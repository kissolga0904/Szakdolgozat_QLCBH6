package com.kissolga.webshop.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageService {
    @Value("${fileuploader.directory}")
    private String uploadDir;

    public Optional<File> serveFile(@PathVariable String fileName) {
        File file = new File(uploadDir, fileName);

        if (!file.exists()) {
            return Optional.empty();
        }

        return Optional.of(file);
    }
}
