package com.daverj.media.service;

import com.daverj.media.dto.mapper.EpisodeMapper;
import com.daverj.media.dto.request.EpisodeCreateDTO;
import com.daverj.media.dto.response.EpisodeDTO;
import com.daverj.media.model.Episode;
import com.daverj.media.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EpisodeService {

    private final EpisodeRepository episodeRepository;
    private final EpisodeMapper episodeMapper;

    public Page<EpisodeDTO> list(Pageable pageable) {
        log.info("Listing all episodes");
        return episodeRepository.findAll(pageable)
                .map(episodeMapper::toDTO);
    }

    public EpisodeDTO create(EpisodeCreateDTO episode) {
        log.info("Creating an Episode for some Tv Show");
        return episodeMapper.toDTO(episodeRepository.save(episodeMapper.toEntity(episode)));
    }

}