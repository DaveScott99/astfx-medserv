package com.daverj.media.service;

import com.daverj.media.dto.mapper.MediaMapper;
import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.request.MediaUpdateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.response.MediaMinDTO;
import com.daverj.media.model.Media;
import com.daverj.media.model.Movie;
import com.daverj.media.repository.MediaRepository;
import com.daverj.media.repository.MovieRepository;
import com.daverj.media.utils.StandardMessage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MediaService {

    private final MediaRepository mediaRepository;
    private final MovieRepository movieRepository;
    private final MediaMapper mediaMapper;

    public Page<MediaMinDTO> findAll(Pageable pageable) {
        return mediaRepository.findAll(pageable)
                .map(mediaMapper::toMinDTO);
    }

    public MediaDTO findByTitle(String title) {
        return mediaRepository.findByTitle(title)
                .map(mediaMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Media not found with title: " + title));
    }

    public MediaMinDTO createMovie(MediaCreateDTO movieCreate) {
        Movie movie = new Movie(mediaMapper.toEntity(movieCreate));
        return mediaMapper.toMinDTO(movieRepository.save(movie));
    }

    public MediaUpdateDTO update(Long id, MediaUpdateDTO mediaDTO) {

        Media entity = mediaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Media not found"));

        Movie newMovie = mediaMapper.toMovieEntityPatch(mediaDTO);

        if (entity != null) {
            if (newMovie.getTitle() != null && !newMovie.getTitle().equals(entity.getTitle())) entity.setTitle(newMovie.getTitle());
            if (newMovie.getOverview() != null && !newMovie.getOverview().equals(entity.getOverview())) entity.setOverview(newMovie.getOverview());
            if (newMovie.getTagline() != null && !newMovie.getTagline().equals(entity.getTagline())) entity.setTagline(newMovie.getTagline());

            mediaRepository.save(entity);
            return mediaDTO;
        }

        return null;
    }

    @Transactional
    public StandardMessage active(Long id) {
        int responseDB = mediaRepository.active(id);
        log.info("Response DB: " + responseDB);
        return new StandardMessage("success", "Movie activated");
    }

    @Transactional
    public StandardMessage disable(Long id) {
        int responseDB = mediaRepository.disable(id);
        log.info("Response DB: " + responseDB);
        return new StandardMessage("success", "Movie disabled");
    }

    @Transactional
    public StandardMessage addGenre(Long mediaId, Long genreId) {
        try{
            mediaRepository.addGenre(mediaId, genreId);
            return new StandardMessage("success", "Genre added");
        }
        catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("Duplicate entry")) {
                throw new DataIntegrityViolationException("This genre is already added");
            }
        }
        return null;
    }

    @Transactional
    public StandardMessage removeGenre(Long mediaID, Long genreId) {
        int responseDB = mediaRepository.removeGenre(mediaID, genreId);
        log.info("Response DB: " + responseDB);
        if (responseDB == 0)
            throw  new DataIntegrityViolationException("This genre is already removed");
        return new StandardMessage("success", "Genre removed");
    }
}
