package com.videoplatform.backend.service;

import com.videoplatform.backend.model.VideoEdit;
import com.videoplatform.backend.model.Video;
import com.videoplatform.backend.model.User;
import com.videoplatform.backend.repository.VideoEditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VideoEditService {

    @Autowired
    private VideoEditRepository videoEditRepository;

    public VideoEdit save(VideoEdit edit) {
        return videoEditRepository.save(edit);
    }

    public Optional<VideoEdit> findById(Long id) {
        return videoEditRepository.findById(id);
    }

    public List<VideoEdit> findByVideo(Video video) {
        return videoEditRepository.findByVideo(video);
    }

    public List<VideoEdit> findByEditor(User editor) {
        return videoEditRepository.findByEditor(editor);
    }

    public List<VideoEdit> findExportedEdits() {
        return videoEditRepository.findExportedEdits();
    }

}