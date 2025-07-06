package com.videoplatform.backend.service;

import com.videoplatform.backend.model.Video;
import jakarta.annotation.PostConstruct;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.progress.ProgressListener;
import net.bramp.ffmpeg.runner.FFmpegExecutor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

@Service
public class VideoProcessingService {

    @Value("${video.ffmpeg.path:ffmpeg}")
    private String ffmpegPath;

    @Value("${video.ffprobe.path:ffprobe}")
    private String ffprobePath;

    private FFmpeg ffmpeg;
    private FFprobe ffprobe;

    @PostConstruct
    public void init() throws IOException {
        ffmpeg = new FFmpeg(ffmpegPath);
        ffprobe = new FFprobe(ffprobePath);
    }

    /**
     * Simple HLS conversion using FFmpeg.
     */
    public void convertToHls(Video video, Path outputDir, int segmentDuration) throws IOException {
        Files.createDirectories(outputDir);
        Path output = outputDir.resolve("playlist.m3u8");

        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(video.getFilePath())
                .addExtraArgs("-profile:v", "baseline", "-level", "3.0", "-start_number", "0")
                .addExtraArgs("-hls_time", String.valueOf(segmentDuration))
                .addExtraArgs("-hls_list_size", "0")
                .addExtraArgs("-f", "hls")
                .addOutput(output.toString())
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        executor.createJob(builder, (ProgressListener) progress -> {
            // Could emit WebSocket progress events here
        }).run();
    }

    public Duration probeDuration(String filePath) throws IOException {
        var info = ffprobe.probe(filePath);
        double seconds = info.getFormat().duration;
        return Duration.ofMillis((long) (seconds * 1000));
    }
}