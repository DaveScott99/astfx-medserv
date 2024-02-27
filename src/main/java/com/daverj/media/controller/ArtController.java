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

import java.util.List;
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

    @GetMapping("/find/backdrop")
    public ResponseEntity<List<ArtDTO>> findBackdropsByMedia(@RequestParam Long mediaId) {
        return new ResponseEntity<>(artService.findBackdropsByMedia(mediaId), HttpStatus.OK);
    }

    @GetMapping("/find/poster")
    public ResponseEntity<List<ArtDTO>> findPostersByMedia(@RequestParam Long mediaId) {
        return new ResponseEntity<>(artService.findPostersByMedia(mediaId), HttpStatus.OK);
    }

    @GetMapping("/find/logo")
    public ResponseEntity<List<ArtDTO>> findLogosByMedia(@RequestParam Long mediaId) {
        return new ResponseEntity<>(artService.findLogosByMedia(mediaId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPoster(@RequestParam Long idMedia, @RequestParam String titleMedia, @RequestParam Long idMediaTmdb, @RequestParam String filePath, @RequestParam String type) {
        artService.createPoster(idMedia, titleMedia, idMediaTmdb, filePath, type);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/select/image")
    public ResponseEntity<StandardMessage> selectImage(@RequestParam Long mediaId, @RequestParam Long imageId, @RequestParam String type) {
        return new ResponseEntity<>(artService.selectImage(mediaId, imageId,  type), HttpStatus.OK);
    }

}
