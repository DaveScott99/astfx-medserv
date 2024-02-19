package com.daverj.media.service;

import com.daverj.media.dto.response.GenreDTO;
import com.daverj.media.dto.mapper.GenreMapper;
import com.daverj.media.model.Genre;
import com.daverj.media.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public List<GenreDTO> list() {
        log.info("Listing all genres");
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<GenreDTO> findByMedia(String title) {
        return genreRepository.findByMediaId(title)
                .stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());
    }

    public GenreDTO create(Genre genre) {
        log.info("Creating genre with name " + genre.getName());
        return genreMapper.toDTO(genreRepository.save(genre));
    }

}