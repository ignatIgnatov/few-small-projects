package com.example.file.model;

import jakarta.persistence.Column;

public class FileUploadResponse {

    private int id;

    private String fileName;

    private String fileUri;

    private String fileDownloadUri;

    private long fileSize;

    private String uploaderName;

    public FileUploadResponse() {
    }

    public FileUploadResponse(int id, String fileName, String fileUri, String fileDownloadUri, long fileSize, String uploaderName) {
        this.id = id;
        this.fileName = fileName;
        this.fileUri = fileUri;
        this.fileDownloadUri = fileDownloadUri;
        this.fileSize = fileSize;
        this.uploaderName = uploaderName;
    }

    public int getId() {
        return id;
    }

    public FileUploadResponse setId(int id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileUploadResponse setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileUri() {
        return fileUri;
    }

    public FileUploadResponse setFileUri(String fileUri) {
        this.fileUri = fileUri;
        return this;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public FileUploadResponse setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
        return this;
    }

    public long getFileSize() {
        return fileSize;
    }

    public FileUploadResponse setFileSize(long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public FileUploadResponse setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
        return this;
    }
}
