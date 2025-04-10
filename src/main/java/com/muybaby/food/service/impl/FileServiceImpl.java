package com.muybaby.food.service.impl;


import com.muybaby.food.service.FileService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {


//    private final String uploadDir = "D:\\project-NEW\\Java\\Food\\uploads" ;
    @Value("${file.upload-dir}") // 从属性文件注入
    private String uploadDir;
    private Path rootLocation;

    @PostConstruct
    public void init() throws IOException {
        this.rootLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(rootLocation);
    }

    @Override
    public Path uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Failed to store empty file");
        }

        // 获取文件的原始名称
        String originalFilename = file.getOriginalFilename();
        String extension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }

        // 生成新的文件名
        String newFilename = UUID.randomUUID().toString() + extension;

        // 创建目标路径 - 直接使用初始化时创建的rootLocation
        Path destinationPath = this.rootLocation.resolve(newFilename);

        // 安全检查 - 确保目标路径在上传目录内
        if (!destinationPath.getParent().normalize().equals(this.rootLocation.normalize())) {
            throw new RuntimeException("Cannot store file outside current directory.");
        }

        // 保存文件
        Files.copy(file.getInputStream(), destinationPath);

        return destinationPath;
    }
}