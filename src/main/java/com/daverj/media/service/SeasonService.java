package com.daverj.media.service;

import com.daverj.media.dto.mapper.SeasonMapper;
import com.daverj.media.dto.request.SeasonCreateDTO;
import com.daverj.media.dto.response.SeasonDTO;
import com.daverj.media.model.Season;
import com.daverj.media.repository.SeasonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SeasonService {

    private final SeasonRepository seasonRepository;
    private final SeasonMapper seasonMapper;

    public Page<SeasonDTO> list(Pageable pageable) {
        log.info("Listing all seasons");
        return seasonRepository.findAll(pageable)
                .map(seasonMapper::toDTO);
    }

    public SeasonDTO create(SeasonCreateDTO season) {
        log.info("Creating an Season");
        return seasonMapper.toDTO(seasonRepository.save(seasonMapper.toEntity(season)));
    }

}