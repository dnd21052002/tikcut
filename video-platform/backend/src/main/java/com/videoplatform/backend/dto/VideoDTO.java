package com.videoplatform.backend.dto;

import com.videoplatform.backend.model.Video;
import java.time.LocalDateTime;

public class VideoDTO {
    
    private Long id;
    private String title;
    private String description;
    private String originalFilename;
    private String thumbnailPath;
    private String hlsPath;
    private Long fileSize;
    private Double durationSeconds;
    private Integer width;
    private Integer height;
    private Double frameRate;
    private Long bitRate;
    private String status;
    private String processingStatus;
    private Boolean isPublic;
    private Long viewCount;
    private Long likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime publishedAt;
    private UserDTO owner;
    
    // Constructors
    public VideoDTO() {}
    
    public VideoDTO(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.originalFilename = video.getOriginalFilename();
        this.thumbnailPath = video.getThumbnailPath();
        this.hlsPath = video.getHlsPath();
        this.fileSize = video.getFileSize();
        this.durationSeconds = video.getDurationSeconds();
        this.width = video.getWidth();
        this.height = video.getHeight();
        this.frameRate = video.getFrameRate();
        this.bitRate = video.getBitRate();
        this.status = video.getStatus() != null ? video.getStatus().name() : null;
        this.processingStatus = video.getProcessingStatus() != null ? video.getProcessingStatus().name() : null;
        this.isPublic = video.getIsPublic();
        this.viewCount = video.getViewCount();
        this.likeCount = video.getLikeCount();
        this.createdAt = video.getCreatedAt();
        this.updatedAt = video.getUpdatedAt();
        this.publishedAt = video.getPublishedAt();
        this.owner = video.getOwner() != null ? new UserDTO(video.getOwner()) : null;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getOriginalFilename() { return originalFilename; }
    public void setOriginalFilename(String originalFilename) { this.originalFilename = originalFilename; }
    
    public String getThumbnailPath() { return thumbnailPath; }
    public void setThumbnailPath(String thumbnailPath) { this.thumbnailPath = thumbnailPath; }
    
    public String getHlsPath() { return hlsPath; }
    public void setHlsPath(String hlsPath) { this.hlsPath = hlsPath; }
    
    public Long getFileSize() { return fileSize; }
    public void setFileSize(Long fileSize) { this.fileSize = fileSize; }
    
    public Double getDurationSeconds() { return durationSeconds; }
    public void setDurationSeconds(Double durationSeconds) { this.durationSeconds = durationSeconds; }
    
    public Integer getWidth() { return width; }
    public void setWidth(Integer width) { this.width = width; }
    
    public Integer getHeight() { return height; }
    public void setHeight(Integer height) { this.height = height; }
    
    public Double getFrameRate() { return frameRate; }
    public void setFrameRate(Double frameRate) { this.frameRate = frameRate; }
    
    public Long getBitRate() { return bitRate; }
    public void setBitRate(Long bitRate) { this.bitRate = bitRate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getProcessingStatus() { return processingStatus; }
    public void setProcessingStatus(String processingStatus) { this.processingStatus = processingStatus; }
    
    public Boolean getIsPublic() { return isPublic; }
    public void setIsPublic(Boolean isPublic) { this.isPublic = isPublic; }
    
    public Long getViewCount() { return viewCount; }
    public void setViewCount(Long viewCount) { this.viewCount = viewCount; }
    
    public Long getLikeCount() { return likeCount; }
    public void setLikeCount(Long likeCount) { this.likeCount = likeCount; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public LocalDateTime getPublishedAt() { return publishedAt; }
    public void setPublishedAt(LocalDateTime publishedAt) { this.publishedAt = publishedAt; }
    
    public UserDTO getOwner() { return owner; }
    public void setOwner(UserDTO owner) { this.owner = owner; }
}