package com.daverj.media.controller;

import com.daverj.media.dto.request.SeasonCreateDTO;
import com.daverj.media.dto.response.SeasonDTO;
import com.daverj.media.service.SeasonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/media/season")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SeasonController {

    private final SeasonService seasonService;

    @GetMapping
    public ResponseEntity<Page<SeasonDTO>> list(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(seasonService.list(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SeasonDTO> create(@RequestBody SeasonCreateDTO season) {
        return new ResponseEntity<>(seasonService.create(season), HttpStatus.CREATED);
    }
}