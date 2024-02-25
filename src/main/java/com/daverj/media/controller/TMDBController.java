package com.daverj.media.controller;

import com.daverj.media.dto.response.ImageTmdbDTO;
import com.daverj.media.service.TMDBService;
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
@RequestMapping("tmdb-api")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TMDBController {

    private final TMDBService tmdbService;

    @GetMapping
    public ResponseEntity<Object> findMovie(@RequestParam Long idMovieTmdb) {
        return new ResponseEntity<>(tmdbService.findMovie(idMovieTmdb), HttpStatus.OK);
    }

    @GetMapping("/movie/images/logo")
    public ResponseEntity<Page<ImageTmdbDTO>> searchLogosByMovie(@PageableDefault(size = 10) Pageable pageable, @RequestParam Long idMovieTmdb) {
        return new ResponseEntity<>(tmdbService.searchLogosByMovie(pageable, idMovieTmdb), HttpStatus.OK);
    }

    @GetMapping("/movie/images/poster")
    public ResponseEntity<Page<ImageTmdbDTO>> searchPostersByMovie(@PageableDefault(size = 10) Pageable pageable, @RequestParam Long idMovieTmdb) {
        return new ResponseEntity<>(tmdbService.searchPostersByMovie(pageable, idMovieTmdb), HttpStatus.OK);
    }

    @GetMapping("/movie/images/backdrop")
    public ResponseEntity<Page<ImageTmdbDTO>> searchBackdropsByMovie(@PageableDefault(size = 10) Pageable pageable, @RequestParam Long idMovieTmdb) {
        return new ResponseEntity<>(tmdbService.searchBackdropsByMovie(pageable, idMovieTmdb), HttpStatus.OK);
    }

}
