package com.daverj.media.controller;

import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media/movie")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {

    private final MovieService movieService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<MediaDTO>> list(Pageable pageable) {
        return new ResponseEntity<>(movieService.list(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MediaDTO> create(@RequestBody MediaCreateDTO movie) {
        return new ResponseEntity<>(movieService.create(movie), HttpStatus.CREATED);
    }
}