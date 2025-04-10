package com.muybaby.food.controller;


import com.muybaby.food.bean.Result;
import com.muybaby.food.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Result<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Path filePath = fileService.uploadFile(file);

            // 构建文件访问URL
            String filename = filePath.getFileName().toString();
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(filename)
                    .toUriString();

            Map<String, String> response = new HashMap<>();
            response.put("filename", filename);
            response.put("url", fileDownloadUri);

            return Result.success(response);
        } catch (IOException e) {
            return Result.error(HttpStatus.BAD_REQUEST, "文件上传失败: " + e.getMessage());
        }
    }
}