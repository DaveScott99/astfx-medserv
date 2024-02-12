package com.daverj.media.service;

import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.request.MediaUpdateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.mapper.MediaMapper;
import com.daverj.media.model.Movie;
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

    public MediaDTO findByTitle(String title) {
        return movieRepository.findByTitle(title)
                .map(mediaMapper::toMovieDTO)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    public MediaDTO findById(Long id) {
        return movieRepository.findById(id)
                .map(mediaMapper::toMovieDTO)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
    }

    public MediaDTO update(Long id, MediaUpdateDTO mediaDTO) {

        Movie entity = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));

        Movie newMovie = mediaMapper.toMovieEntityPatch(mediaDTO);

        if (entity != null) {
            if (newMovie.getTitle() != null && !newMovie.getTitle().equals(entity.getTitle())) entity.setTitle(newMovie.getTitle());
            if (newMovie.getBackdrop() != null && !newMovie.getBackdrop().equals(entity.getBackdrop())) entity.setBackdrop(newMovie.getBackdrop());
            if (newMovie.getPoster() != null && !newMovie.getPoster().equals(entity.getPoster())) entity.setPoster(newMovie.getPoster());
            if (newMovie.getLogo() != null && !newMovie.getLogo().equals(entity.getLogo())) entity.setLogo(newMovie.getLogo());
            if (newMovie.getOverview() != null && !newMovie.getOverview().equals(entity.getOverview())) entity.setOverview(newMovie.getOverview());
            if (newMovie.getDescription() != null && !newMovie.getDescription().equals(entity.getDescription())) entity.setDescription(newMovie.getDescription());

            return mediaMapper.toMovieDTO(movieRepository.save(entity));
        }

        return null;
    }

    public String delete(Long id) {
        try {
            movieRepository.findById(id)
                    .map(movie -> {
                        movieRepository.deleteById(id);
                        return "Movie deleted";
                    })
                    .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Violação de integridade");
        }

        return "";
    }

    public MediaDTO create(MediaCreateDTO movie) {
        log.info("Movie created with id " + movie.getId());
        return mediaMapper.toMovieDTO(movieRepository.save(mediaMapper.toMovieEntity(movie)));
    }

}