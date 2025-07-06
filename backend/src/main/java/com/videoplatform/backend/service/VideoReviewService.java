package com.videoplatform.backend.service;

import com.videoplatform.backend.model.VideoReview;
import com.videoplatform.backend.model.Video;
import com.videoplatform.backend.model.User;
import com.videoplatform.backend.repository.VideoReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VideoReviewService {

    @Autowired
    private VideoReviewRepository videoReviewRepository;

    public VideoReview save(VideoReview review) {
        return videoReviewRepository.save(review);
    }

    public Optional<VideoReview> findById(Long id) {
        return videoReviewRepository.findById(id);
    }

    public List<VideoReview> findPendingReviews() {
        return videoReviewRepository.findPendingReviewsOrderByCreatedAt();
    }

    public VideoReview approve(VideoReview review, User reviewer) {
        review.setStatus(VideoReview.ReviewStatus.APPROVED);
        review.setReviewer(reviewer);
        review.setReviewedAt(LocalDateTime.now());
        return videoReviewRepository.save(review);
    }

    public VideoReview reject(VideoReview review, User reviewer, String reason) {
        review.setStatus(VideoReview.ReviewStatus.REJECTED);
        review.setReviewer(reviewer);
        review.setRejectionReason(reason);
        review.setReviewedAt(LocalDateTime.now());
        return videoReviewRepository.save(review);
    }

}