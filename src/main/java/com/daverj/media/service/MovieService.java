package com.daverj.media.service;

import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.request.MediaUpdateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.mapper.MediaMapper;
import com.daverj.media.dto.response.MediaMinDTO;
import com.daverj.media.model.Movie;
import com.daverj.media.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
                .map(mediaMapper::toDTO);
    }

    public MediaDTO findByTitle(String title) {
        return movieRepository.findByTitle(title)
                .map(mediaMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    public MediaDTO findById(Long id) {
        return movieRepository.findById(id)
                .map(mediaMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    public MediaDTO update(Long id, MediaUpdateDTO mediaDTO) {

        Movie entity = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        Movie newMovie = mediaMapper.toMovieEntityPatch(mediaDTO);

        if (entity != null) {
            if (newMovie.getTitle() != null && !newMovie.getTitle().equals(entity.getTitle())) entity.setTitle(newMovie.getTitle());
            if (newMovie.getOverview() != null && !newMovie.getOverview().equals(entity.getOverview())) entity.setOverview(newMovie.getOverview());
            if (newMovie.getTagline() != null && !newMovie.getTagline().equals(entity.getTagline())) entity.setTagline(newMovie.getTagline());

            return mediaMapper.toDTO(movieRepository.save(entity));
        }

        return null;
    }

    @Transactional
    public void active(Long id) {

        int responseDB = movieRepository.active(id);

        log.info("Response DB: " + responseDB);

    }

    @Transactional
    public void disable(Long id) {

        int responseDB = movieRepository.disable(id);

        log.info("Response DB: " + responseDB);

    }

    public MediaMinDTO create(MediaCreateDTO movieCreate) {
        Movie movie = new Movie(mediaMapper.toEntity(movieCreate));
        return mediaMapper.toMinDTO(movieRepository.save(movie));
    }

    @Transactional
    public void addGenre(Long movieId, Long genreId) {

        int responseDB = movieRepository.addGenre(movieId, genreId);

        log.info("Response DB: " + responseDB);

    }

    @Transactional
    public void removeGenre(Long movieId, Long genreId) {

        int responseDB = movieRepository.removeGenre(movieId, genreId);

        log.info("Response DB: " + responseDB);
    }


}