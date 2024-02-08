package com.daverj.media.controller;

import com.daverj.media.dto.response.TimelineDTO;
import com.daverj.media.service.TimelineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/media/timeline")
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TimelineController {

    private final TimelineService timelineService;

    @GetMapping
    public ResponseEntity<Page<TimelineDTO>> buildTimeline(@PageableDefault(size = 5) Pageable pageable) {
        return new ResponseEntity<>(timelineService.buildTimeline(pageable), HttpStatus.OK);
    }

}
