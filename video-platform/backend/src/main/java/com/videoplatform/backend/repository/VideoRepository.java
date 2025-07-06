package com.videoplatform.backend.repository;

import com.videoplatform.backend.model.Video;
import com.videoplatform.backend.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    
    List<Video> findByOwner(User owner);
    
    Page<Video> findByOwner(User owner, Pageable pageable);
    
    List<Video> findByStatus(Video.VideoStatus status);
    
    Page<Video> findByStatus(Video.VideoStatus status, Pageable pageable);
    
    List<Video> findByIsPublicTrue();
    
    Page<Video> findByIsPublicTrue(Pageable pageable);
    
    @Query("SELECT v FROM Video v WHERE v.isPublic = true AND v.status = :status")
    Page<Video> findPublicVideosByStatus(@Param("status") Video.VideoStatus status, Pageable pageable);
    
    @Query("SELECT v FROM Video v WHERE v.owner = :owner AND v.status = :status")
    List<Video> findByOwnerAndStatus(@Param("owner") User owner, @Param("status") Video.VideoStatus status);
    
    @Query("SELECT v FROM Video v WHERE v.title LIKE %:keyword% OR v.description LIKE %:keyword%")
    Page<Video> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT v FROM Video v WHERE v.isPublic = true AND (v.title LIKE %:keyword% OR v.description LIKE %:keyword%)")
    Page<Video> searchPublicVideosByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
    @Query("SELECT v FROM Video v WHERE v.createdAt BETWEEN :startDate AND :endDate")
    List<Video> findByCreatedAtBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT v FROM Video v WHERE v.status = 'UPLOADED' AND v.processingStatus = 'PENDING'")
    List<Video> findVideosNeedingProcessing();
    
    @Query("SELECT v FROM Video v WHERE v.status = 'PROCESSED' AND EXISTS (SELECT r FROM VideoReview r WHERE r.video = v AND r.status = 'PENDING')")
    List<Video> findVideosNeedingReview();
    
    @Query("SELECT v FROM Video v ORDER BY v.viewCount DESC")
    Page<Video> findMostPopular(Pageable pageable);
    
    @Query("SELECT v FROM Video v WHERE v.isPublic = true ORDER BY v.publishedAt DESC")
    Page<Video> findRecentlyPublished(Pageable pageable);
}