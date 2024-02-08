package com.daverj.media.service;

import com.daverj.media.dto.response.GenreDTO;
import com.daverj.media.dto.mapper.GenreMapper;
import com.daverj.media.model.Genre;
import com.daverj.media.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public Iterable<GenreDTO> list(Pageable pageable) {
        log.info("Listing all genres");
        return genreRepository.findAll(pageable)
                .map(genreMapper::toDTO);
    }

    public GenreDTO create(Genre genre) {
        log.info("Creating genre with name " + genre.getName());
        return genreMapper.toDTO(genreRepository.save(genre));
    }

}