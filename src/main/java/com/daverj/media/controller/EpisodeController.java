package com.daverj.media.controller;

import com.daverj.media.dto.request.EpisodeCreateDTO;
import com.daverj.media.dto.response.EpisodeDTO;
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


@RestController
@RequestMapping("/media/episode")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EpisodeController {

    private final EpisodeService episodeService;

    @GetMapping
    public ResponseEntity<Page<EpisodeDTO>> list(Pageable pageable) {
        return new ResponseEntity<>(episodeService.list(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EpisodeDTO> create(@RequestBody EpisodeCreateDTO episode) {
        return new ResponseEntity<>(episodeService.create(episode), HttpStatus.CREATED);
    }
}