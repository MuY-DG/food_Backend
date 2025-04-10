package com.muybaby.food.service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Path;

public interface FileService {
    Path uploadFile(MultipartFile file) throws IOException;
}