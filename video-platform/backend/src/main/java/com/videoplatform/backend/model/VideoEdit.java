package com.videoplatform.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "video_edits")
public class VideoEdit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "edit_data", columnDefinition = "TEXT")
    private String editData; // JSON string containing edit configurations
    
    @Column(name = "output_path")
    private String outputPath;
    
    @Column(name = "output_hls_path")
    private String outputHlsPath;
    
    @Column(name = "thumbnail_path")
    private String thumbnailPath;
    
    @Column(name = "duration_seconds")
    private Double durationSeconds;
    
    @Enumerated(EnumType.STRING)
    private EditStatus status = EditStatus.DRAFT;
    
    @Column(name = "is_exported")
    private Boolean isExported = false;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    @NotNull
    private Video video;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editor_id")
    private User editor;
    
    public enum EditStatus {
        DRAFT, IN_PROGRESS, COMPLETED, EXPORTED, PUBLISHED
    }
    
    // Constructors
    public VideoEdit() {}
    
    public VideoEdit(String name, Video video, User editor) {
        this.name = name;
        this.video = video;
        this.editor = editor;
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
    
    public EditStatus getStatus() { return status; }
    public void setStatus(EditStatus status) { this.status = status; }
    
    public Boolean getIsExported() { return isExported; }
    public void setIsExported(Boolean isExported) { this.isExported = isExported; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public Video getVideo() { return video; }
    public void setVideo(Video video) { this.video = video; }
    
    public User getEditor() { return editor; }
    public void setEditor(User editor) { this.editor = editor; }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}