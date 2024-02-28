package com.daverj.media.controller;

import com.daverj.media.dto.response.ImageDTO;
import com.daverj.media.service.ImageService;
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
@RequestMapping("/media/image")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageController {

    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<Map<String, Set<ImageDTO>>> findAllByMedia(@RequestParam Long id) {
        return new ResponseEntity<>(imageService.findAllByMedia(id), HttpStatus.OK);
    }

    @GetMapping("/find/backdrop")
    public ResponseEntity<List<ImageDTO>> findBackdropsByMedia(@RequestParam Long mediaId) {
        return new ResponseEntity<>(imageService.findBackdropsByMedia(mediaId), HttpStatus.OK);
    }

    @GetMapping("/find/poster")
    public ResponseEntity<List<ImageDTO>> findPostersByMedia(@RequestParam Long mediaId) {
        return new ResponseEntity<>(imageService.findPostersByMedia(mediaId), HttpStatus.OK);
    }

    @GetMapping("/find/logo")
    public ResponseEntity<List<ImageDTO>> findLogosByMedia(@RequestParam Long mediaId) {
        return new ResponseEntity<>(imageService.findLogosByMedia(mediaId), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPoster(@RequestParam Long idMedia, @RequestParam String titleMedia, @RequestParam Long idMediaTmdb, @RequestParam String filePath, @RequestParam String type) {
        imageService.createPoster(idMedia, titleMedia, idMediaTmdb, filePath, type);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/select/image")
    public ResponseEntity<StandardMessage> selectImage(@RequestParam Long mediaId, @RequestParam Long imageId, @RequestParam String type) {
        return new ResponseEntity<>(imageService.selectImage(mediaId, imageId,  type), HttpStatus.OK);
    }

}
