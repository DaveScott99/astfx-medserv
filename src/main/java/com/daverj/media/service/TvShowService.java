package com.daverj.media.service;

import com.daverj.media.dto.mapper.TvShowMapper;
import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.mapper.MediaMapper;
import com.daverj.media.dto.response.TvShowDTO;
import com.daverj.media.repository.TvShowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TvShowService {

    private final TvShowRepository tvShowRepository;
    private final MediaMapper mediaMapper;
    private final TvShowMapper tvShowMapper;

    public Page<MediaDTO> list(Pageable pageable) {
        log.info("Listing all Tv Shows");
        return tvShowRepository.findAll(pageable)
                .map(mediaMapper::toTvShowDTO);
    }

    public TvShowDTO findById(Long id) {
        return tvShowRepository.findById(id)
                .map(tvShowMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Tv Show not found "));
    }

    public MediaDTO create(MediaCreateDTO tvShow) {
        log.info("Creating an Tv Show with id " + tvShow.getId());
        return mediaMapper.toTvShowDTO(tvShowRepository.save(mediaMapper.toTvShowEntity(tvShow)));
    }

}