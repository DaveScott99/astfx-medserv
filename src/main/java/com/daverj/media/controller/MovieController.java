package com.daverj.media.controller;

import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.request.MediaUpdateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.service.MovieService;
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
@RequestMapping("/media/movie")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<Page<MediaDTO>> list(Pageable pageable) {
        return new ResponseEntity<>(movieService.list(pageable), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<MediaDTO> findById(@RequestParam Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @GetMapping("/find")
    public ResponseEntity<MediaDTO> findByTitle(@RequestParam String title) {
        String titleDecoded = URLDecoder.decode(title, StandardCharsets.UTF_8);
        return ResponseEntity.ok().body(movieService.findByTitle(title));
    }

    @PostMapping
    public ResponseEntity<MediaDTO> create(@RequestBody MediaCreateDTO movie) {
        return new ResponseEntity<>(movieService.create(movie), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<MediaDTO> update(@RequestParam Long id, @RequestBody MediaUpdateDTO  dto) {
        return new ResponseEntity<>(movieService.update(id, dto), HttpStatus.OK);
    }


    @DeleteMapping
    public ResponseEntity<String> delete(@RequestParam Long id) {
        return ResponseEntity.ok(movieService.delete(id));
    }
}