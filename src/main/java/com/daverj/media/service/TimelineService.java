package com.daverj.media.service;

import com.daverj.media.dto.response.TimelineDTO;
import com.daverj.media.dto.mapper.GenreMapper;
import com.daverj.media.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TimelineService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public Page<TimelineDTO> buildTimeline(Pageable pageable) {
        return genreRepository.findAll(pageable)
                .map(genreMapper::toDTOTimeline);
    }

}
