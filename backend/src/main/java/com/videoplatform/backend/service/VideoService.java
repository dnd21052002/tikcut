package com.videoplatform.backend.service;

import com.videoplatform.backend.model.Video;
import com.videoplatform.backend.model.User;
import com.videoplatform.backend.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    public Video save(Video video) {
        return videoRepository.save(video);
    }

    public Optional<Video> findById(Long id) {
        return videoRepository.findById(id);
    }

    public List<Video> findByOwner(User owner) {
        return videoRepository.findByOwner(owner);
    }

    public Page<Video> findByOwner(User owner, Pageable pageable) {
        return videoRepository.findByOwner(owner, pageable);
    }

    public Page<Video> findPublicVideos(Pageable pageable) {
        return videoRepository.findByIsPublicTrue(pageable);
    }

    public Video publishVideo(Video video) {
        video.setIsPublic(true);
        video.setStatus(Video.VideoStatus.PUBLISHED);
        video.setPublishedAt(LocalDateTime.now());
        return videoRepository.save(video);
    }

    public void deleteVideo(Video video) {
        video.setStatus(Video.VideoStatus.DELETED);
        videoRepository.save(video);
    }

    public List<Video> searchPublic(String keyword, Pageable pageable) {
        return videoRepository.searchPublicVideosByKeyword(keyword, pageable).getContent();
    }

}