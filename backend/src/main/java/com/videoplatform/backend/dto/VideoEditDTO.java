package com.videoplatform.backend.dto;

import com.videoplatform.backend.model.VideoEdit;
import java.time.LocalDateTime;

public class VideoEditDTO {
    
    private Long id;
    private String name;
    private String description;
    private String editData;
    private String outputPath;
    private String outputHlsPath;
    private String thumbnailPath;
    private Double durationSeconds;
    private String status;
    private Boolean isExported;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long videoId;
    private UserDTO editor;
    
    // Constructors
    public VideoEditDTO() {}
    
    public VideoEditDTO(VideoEdit videoEdit) {
        this.id = videoEdit.getId();
        this.name = videoEdit.getName();
        this.description = videoEdit.getDescription();
        this.editData = videoEdit.getEditData();
        this.outputPath = videoEdit.getOutputPath();
        this.outputHlsPath = videoEdit.getOutputHlsPath();
        this.thumbnailPath = videoEdit.getThumbnailPath();
        this.durationSeconds = videoEdit.getDurationSeconds();
        this.status = videoEdit.getStatus() != null ? videoEdit.getStatus().name() : null;
        this.isExported = videoEdit.getIsExported();
        this.createdAt = videoEdit.getCreatedAt();
        this.updatedAt = videoEdit.getUpdatedAt();
        this.videoId = videoEdit.getVideo() != null ? videoEdit.getVideo().getId() : null;
        this.editor = videoEdit.getEditor() != null ? new UserDTO(videoEdit.getEditor()) : null;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getEditData() { return editData; }
    public void setEditData(String editData) { this.editData = editData; }
    
    public String getOutputPath() { return outputPath; }
    public void setOutputPath(String outputPath) { this.outputPath = outputPath; }
    
    public String getOutputHlsPath() { return outputHlsPath; }
    public void setOutputHlsPath(String outputHlsPath) { this.outputHlsPath = outputHlsPath; }
    
    public String getThumbnailPath() { return thumbnailPath; }
    public void setThumbnailPath(String thumbnailPath) { this.thumbnailPath = thumbnailPath; }
    
    public Double getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(Double durationSeconds) { this.durationSeconds = durationSeconds; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Boolean getIsExported() { return isExported; }
    public void setIsExported(Boolean isExported) { this.isExported = isExported; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public Long getVideoId() { return videoId; }
    public void setVideoId(Long videoId) { this.videoId = videoId; }
    
    public UserDTO getEditor() { return editor; }
    public void setEditor(UserDTO editor) { this.editor = editor; }
}