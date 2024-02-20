package com.daverj.media.controller;

import com.daverj.media.dto.response.ArtDTO;
import com.daverj.media.model.Art;
import com.daverj.media.service.ArtService;
import com.daverj.media.utils.StandardMessage;
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

    @PatchMapping("/select/logo")
    public ResponseEntity<StandardMessage> selectLogo(@RequestParam Long mediaId, @RequestParam Long logoId) {
        return new ResponseEntity<>(artService.selectLogo(mediaId, logoId), HttpStatus.OK);
    }

    @PatchMapping("/select/poster")
    public ResponseEntity<StandardMessage> selectPoster(@RequestParam Long mediaId, @RequestParam Long posterId) {
        return new ResponseEntity<>(artService.selectPoster(mediaId, posterId), HttpStatus.OK);
    }

    @PatchMapping("/select/backdrop")
    public ResponseEntity<StandardMessage> selectBackdrop(@RequestParam Long mediaId, @RequestParam Long backdropId) {
        return new ResponseEntity<>(artService.selectBackdrop(mediaId, backdropId), HttpStatus.OK);
    }

}
