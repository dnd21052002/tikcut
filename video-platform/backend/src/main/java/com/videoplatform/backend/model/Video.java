package com.videoplatform.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "videos")
public class Video {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotBlank
    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "original_filename")
    private String originalFilename;
    
    @Column(name = "file_path")
    private String filePath;
    
    @Column(name = "thumbnail_path")
    private String thumbnailPath;
    
    @Column(name = "hls_path")
    private String hlsPath;
    
    @Column(name = "file_size")
    private Long fileSize;
    
    @Column(name = "duration_seconds")
    private Double durationSeconds;
    
    @Column(name = "width")
    private Integer width;
    
    @Column(name = "height")
    private Integer height;
    
    @Column(name = "frame_rate")
    private Double frameRate;
    
    @Column(name = "bit_rate")
    private Long bitRate;
    
    @Enumerated(EnumType.STRING)
    private VideoStatus status = VideoStatus.UPLOADED;
    
    @Enumerated(EnumType.STRING)
    private ProcessingStatus processingStatus = ProcessingStatus.PENDING;
    
    @Column(name = "is_public")
    private Boolean isPublic = false;
    
    @Column(name = "view_count")
    private Long viewCount = 0L;
    
    @Column(name = "like_count")
    private Long likeCount = 0L;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @Column(name = "published_at")
    private LocalDateTime publishedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @NotNull
    private User owner;
    
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VideoEdit> videoEdits = new ArrayList<>();
    
    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VideoReview> videoReviews = new ArrayList<>();
    
    public enum VideoStatus {
        UPLOADED, PROCESSING, PROCESSED, PUBLISHED, REJECTED, DELETED
    }
    
    public enum ProcessingStatus {
        PENDING, IN_PROGRESS, COMPLETED, FAILED
    }
    
    // Constructors
    public Video() {}
    
    public Video(String title, String originalFilename, String filePath, User owner) {
        this.title = title;
        this.originalFilename = originalFilename;
        this.filePath = filePath;
        this.owner = owner;
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
    
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    
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
    
    public VideoStatus getStatus() { return status; }
    public void setStatus(VideoStatus status) { this.status = status; }
    
    public ProcessingStatus getProcessingStatus() { return processingStatus; }
    public void setProcessingStatus(ProcessingStatus processingStatus) { this.processingStatus = processingStatus; }
    
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
    
    public User getOwner() { return owner; }
    public void setOwner(User owner) { this.owner = owner; }
    
    public List<VideoEdit> getVideoEdits() { return videoEdits; }
    public void setVideoEdits(List<VideoEdit> videoEdits) { this.videoEdits = videoEdits; }
    
    public List<VideoReview> getVideoReviews() { return videoReviews; }
    public void setVideoReviews(List<VideoReview> videoReviews) { this.videoReviews = videoReviews; }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}