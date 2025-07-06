package com.videoplatform.backend.repository;

import com.videoplatform.backend.model.VideoReview;
import com.videoplatform.backend.model.Video;
import com.videoplatform.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoReviewRepository extends JpaRepository<VideoReview, Long> {
    
    List<VideoReview> findByVideo(Video video);
    
    List<VideoReview> findByReviewer(User reviewer);
    
    List<VideoReview> findByStatus(VideoReview.ReviewStatus status);
    
    Optional<VideoReview> findByVideoAndStatus(Video video, VideoReview.ReviewStatus status);
    
    @Query("SELECT vr FROM VideoReview vr WHERE vr.status = 'PENDING' ORDER BY vr.createdAt ASC")
    List<VideoReview> findPendingReviewsOrderByCreatedAt();
    
    @Query("SELECT vr FROM VideoReview vr WHERE vr.reviewer = :reviewer AND vr.status = :status")
    List<VideoReview> findByReviewerAndStatus(@Param("reviewer") User reviewer, @Param("status") VideoReview.ReviewStatus status);
    
    @Query("SELECT vr FROM VideoReview vr WHERE vr.video = :video ORDER BY vr.createdAt DESC")
    List<VideoReview> findByVideoOrderByCreatedAtDesc(@Param("video") Video video);
    
    @Query("SELECT COUNT(vr) FROM VideoReview vr WHERE vr.reviewer = :reviewer")
    Long countReviewsByReviewer(@Param("reviewer") User reviewer);
    
    @Query("SELECT COUNT(vr) FROM VideoReview vr WHERE vr.status = 'PENDING'")
    Long countPendingReviews();
}