package com.daverj.media.controller;

import com.daverj.media.ImageRecord;
import com.daverj.media.dto.response.ImagesTmdbDTO;
import com.daverj.media.utils.UtilMethods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("tmdb-api")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TMDBController {

    private final String TMDB_TOKEN = "39fcbb59ca74494324fa1fdc9407bd52";
    private final UtilMethods utilMethods;

    @GetMapping
    public ResponseEntity<Object> findMovie(@RequestParam Long idMovieTmdb) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate
                .getForEntity("https://api.themoviedb.org/3/movie/"+idMovieTmdb+"?language=en-US&api_key="+TMDB_TOKEN, Object.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @GetMapping("/movie/images/logo")
    public ResponseEntity<List<ImageRecord>> searchLogosByMovie(@RequestParam Long idMovieTmdb) {
        RestTemplate restTemplate = new RestTemplate();
        ImagesTmdbDTO response = restTemplate
                .getForObject("https://api.themoviedb.org/3/movie/"+idMovieTmdb+"/images?language=en&api_key="+TMDB_TOKEN, ImagesTmdbDTO.class);

        List<ImageRecord> logos = response.getLogos();

        return new ResponseEntity<>(logos, HttpStatus.OK);
    }

    @GetMapping("/movie/images/poster")
    public ResponseEntity<Page<ImageRecord>> searchPostersByMovie(@PageableDefault(size = 10) Pageable pageable, @RequestParam Long idMovieTmdb) {
        RestTemplate restTemplate = new RestTemplate();
        ImagesTmdbDTO response = restTemplate
                .getForObject("https://api.themoviedb.org/3/movie/"+idMovieTmdb+"/images?language=en&api_key="+TMDB_TOKEN, ImagesTmdbDTO.class);

        List<ImageRecord> posters = response.getPosters();

        return new ResponseEntity<>(utilMethods.convertListToPagination(pageable, posters), HttpStatus.OK);
    }

}
