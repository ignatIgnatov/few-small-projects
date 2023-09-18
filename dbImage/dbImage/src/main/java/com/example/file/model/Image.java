package com.example.file.model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String fileName;
    @Column
    private String fileUri;
    @Column
    private String fileDownloadUri;
    @Column
    private long fileSize;
    @Column
    private String uploaderName;

    public Image(String fileName, String fileUri, String fileDownloadUri, long fileSize, String uploaderName) {

        this.fileName = fileName;
        this.fileUri = fileUri;
        this.fileDownloadUri = fileDownloadUri;
        this.fileSize = fileSize;
        this.uploaderName = uploaderName;
    }

    public Image() {
    }

    public int getId() {
        return id;
    }

    public Image setId(int id) {
        this.id = id;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public Image setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileUri() {
        return fileUri;
    }

    public Image setFileUri(String fileUri) {
        this.fileUri = fileUri;
        return this;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public Image setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
        return this;
    }

    public long getFileSize() {
        return fileSize;
    }

    public Image setFileSize(long fileSize) {
        this.fileSize = fileSize;
        return this;
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public Image setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName;
        return this;
    }
}
