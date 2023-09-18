package com.example.file.service;

import com.example.file.model.FileUploadResponse;
import com.example.file.model.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileUploadService {

    public FileUploadResponse uploadFile(MultipartFile file, String uploaderName) throws IOException;
    public Resource fetchFileAsResource(String fileName) throws FileNotFoundException;
    public List<Image> getAllFiles();
}
