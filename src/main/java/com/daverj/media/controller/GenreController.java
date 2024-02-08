package com.daverj.media.controller;

import com.daverj.media.dto.response.GenreDTO;
import com.daverj.media.model.Genre;
import com.daverj.media.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/media/genre")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<Iterable<GenreDTO>> list(Pageable pageable) {
        return new ResponseEntity<>(genreService.list(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> create(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.create(genre), HttpStatus.CREATED);
    }

}