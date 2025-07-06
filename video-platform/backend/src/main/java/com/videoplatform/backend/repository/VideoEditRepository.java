package com.videoplatform.backend.repository;

import com.videoplatform.backend.model.VideoEdit;
import com.videoplatform.backend.model.Video;
import com.videoplatform.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoEditRepository extends JpaRepository<VideoEdit, Long> {
    
    List<VideoEdit> findByVideo(Video video);
    
    List<VideoEdit> findByEditor(User editor);
    
    List<VideoEdit> findByStatus(VideoEdit.EditStatus status);
    
    @Query("SELECT ve FROM VideoEdit ve WHERE ve.video = :video AND ve.editor = :editor")
    List<VideoEdit> findByVideoAndEditor(@Param("video") Video video, @Param("editor") User editor);
    
    @Query("SELECT ve FROM VideoEdit ve WHERE ve.video = :video AND ve.status = :status")
    List<VideoEdit> findByVideoAndStatus(@Param("video") Video video, @Param("status") VideoEdit.EditStatus status);
    
    @Query("SELECT ve FROM VideoEdit ve WHERE ve.editor = :editor AND ve.status = :status")
    List<VideoEdit> findByEditorAndStatus(@Param("editor") User editor, @Param("status") VideoEdit.EditStatus status);
    
    @Query("SELECT ve FROM VideoEdit ve WHERE ve.isExported = true AND ve.status = 'EXPORTED'")
    List<VideoEdit> findExportedEdits();
    
    @Query("SELECT ve FROM VideoEdit ve WHERE ve.video.owner = :owner ORDER BY ve.updatedAt DESC")
    List<VideoEdit> findByVideoOwnerOrderByUpdatedAtDesc(@Param("owner") User owner);
}