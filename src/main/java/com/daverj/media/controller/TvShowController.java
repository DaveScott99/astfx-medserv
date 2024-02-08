package com.daverj.media.controller;

import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.response.TvShowDTO;
import com.daverj.media.service.TvShowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/media/tv-show")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TvShowController {

    private final TvShowService tvShowService;

    @GetMapping("/list")
    public ResponseEntity<Page<MediaDTO>> list(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(tvShowService.list(pageable), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TvShowDTO> findById(@RequestParam Long id) {
        return ResponseEntity.ok(tvShowService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MediaDTO> create(@RequestBody MediaCreateDTO tvShow) {
        return new ResponseEntity<>(tvShowService.create(tvShow), HttpStatus.CREATED);
    }
}