package com.daverj.media.controller;

import com.daverj.media.dto.response.GenreDTO;
import com.daverj.media.model.Genre;
import com.daverj.media.service.GenreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media/genre")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> list() {
        return new ResponseEntity<>(genreService.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> create(@RequestBody Genre genre) {
        return new ResponseEntity<>(genreService.create(genre), HttpStatus.CREATED);
    }

}