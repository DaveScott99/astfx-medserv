package com.daverj.media.service;

import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.mapper.MediaMapper;
import com.daverj.media.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {

    private final MovieRepository movieRepository;
    private final MediaMapper mediaMapper;

    public Page<MediaDTO> list(Pageable pageable) {
        log.info("Listing all movies");
        return movieRepository.findAll(pageable)
                .map(mediaMapper::toMovieDTO);
    }

    public MediaDTO findById(Long id) {
        return movieRepository.findById(id)
                .map(mediaMapper::toMovieDTO)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    public MediaDTO create(MediaCreateDTO movie) {
        log.info("Movie created with id " + movie.getId());
        return mediaMapper.toMovieDTO(movieRepository.save(mediaMapper.toMovieEntity(movie)));
    }

}