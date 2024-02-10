package com.daverj.media.controller;

import com.daverj.media.dto.request.EpisodeCreateDTO;
import com.daverj.media.dto.request.EpisodeUpdateDTO;
import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.request.MediaUpdateDTO;
import com.daverj.media.dto.response.EpisodeDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.response.TvShowDTO;
import com.daverj.media.model.Episode;
import com.daverj.media.service.EpisodeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;


@RestController
@RequestMapping("/media/episode")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EpisodeController {

    private final EpisodeService episodeService;

    @GetMapping("/list")
    public ResponseEntity<Page<EpisodeDTO>> list(Pageable pageable) {
        return new ResponseEntity<>(episodeService.list(pageable), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<EpisodeDTO> findByTitle(@RequestParam String title) {
        String titleDecoded = URLDecoder.decode(title, StandardCharsets.UTF_8);
        return ResponseEntity.ok().body(episodeService.findByTitle(title));
    }

    @GetMapping
    public ResponseEntity<EpisodeDTO> findById(@RequestParam Long id) {
        return ResponseEntity.ok(episodeService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EpisodeDTO> create(@RequestBody EpisodeCreateDTO episode) {
        return new ResponseEntity<>(episodeService.create(episode), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<EpisodeDTO> update(@RequestParam Long id, @RequestBody EpisodeUpdateDTO dto) {
        return new ResponseEntity<>(episodeService.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long id) {
        return ResponseEntity.ok(episodeService.delete(id));
    }

}