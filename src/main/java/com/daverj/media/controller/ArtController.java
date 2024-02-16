package com.daverj.media.controller;

import com.daverj.media.dto.response.ArtDTO;
import com.daverj.media.model.Art;
import com.daverj.media.service.ArtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/media/art")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArtController {

    private final ArtService artService;

    @GetMapping
    public ResponseEntity<Map<String, Set<ArtDTO>>> findAllByMedia(@RequestParam Long id) {
        return new ResponseEntity<>(artService.findAllByMedia(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArtDTO> create(@RequestBody Art art) {
        return new ResponseEntity<>(artService.create(art), HttpStatus.CREATED);
    }

}
