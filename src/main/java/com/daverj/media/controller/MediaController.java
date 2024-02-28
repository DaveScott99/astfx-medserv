package com.daverj.media.controller;

import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.request.MediaUpdateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.response.MediaMinDTO;
import com.daverj.media.service.MediaService;
import com.daverj.media.utils.StandardMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/media")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MediaController {

    private final MediaService mediaService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<MediaMinDTO>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(mediaService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{title}", produces = "application/json")
    public ResponseEntity<MediaDTO> findByTitle(@PathVariable String title) {
        String titleDecoded = URLDecoder.decode(title, StandardCharsets.UTF_8);
        return new ResponseEntity<>(mediaService.findByTitle(titleDecoded), HttpStatus.OK);
    }

    @PostMapping("/create/movie")
    public ResponseEntity<MediaMinDTO> create(@Valid @RequestBody MediaCreateDTO movie) {
        return new ResponseEntity<>(mediaService.createMovie(movie), HttpStatus.CREATED);
    }

    @PatchMapping("/{mediaId}")
    public ResponseEntity<MediaUpdateDTO> update(@PathVariable Long mediaId, @RequestBody MediaUpdateDTO dto) {
        return new ResponseEntity<>(mediaService.update(mediaId, dto), HttpStatus.OK);
    }

    @PostMapping("/{mediaId}/add/genre/{genreId}")
    public ResponseEntity<StandardMessage> addGenre(@PathVariable Long mediaId, @PathVariable Long genreId) {
        return new ResponseEntity<>(mediaService.addGenre(mediaId, genreId) ,HttpStatus.OK);
    }

    @DeleteMapping("/{mediaId}/remove/genre/{genreId}")
    public ResponseEntity<StandardMessage> removeGenre(@PathVariable Long mediaId, @PathVariable Long genreId) {
        return new ResponseEntity<>(mediaService.removeGenre(mediaId, genreId),HttpStatus.OK);
    }

    @PatchMapping("/active/{mediaId}")
    public ResponseEntity<StandardMessage> active(@PathVariable Long mediaId) {
        return new ResponseEntity<>(mediaService.active(mediaId), HttpStatus.OK);
    }

    @PatchMapping("/disable/{mediaId}")
    public ResponseEntity<StandardMessage> disable(@PathVariable Long mediaId) {
        return new ResponseEntity<>(mediaService.disable(mediaId), HttpStatus.OK);
    }

}