package com.daverj.media.service;

import com.daverj.media.dto.mapper.TvShowMapper;
import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.request.MediaUpdateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.mapper.MediaMapper;
import com.daverj.media.dto.response.TvShowDTO;
import com.daverj.media.model.Movie;
import com.daverj.media.model.TvShow;
import com.daverj.media.repository.TvShowRepository;
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
public class TvShowService {

    private final TvShowRepository tvShowRepository;
    private final MediaMapper mediaMapper;
    private final TvShowMapper tvShowMapper;

    public Page<MediaDTO> list(Pageable pageable) {
        log.info("Listing all Tv Shows");
        return tvShowRepository.findAll(pageable)
                .map(mediaMapper::toTvShowDTO);
    }

    public MediaDTO findByTitle(String title) {
        return tvShowRepository.findByTitle(title)
                .map(mediaMapper::toTvShowDTO)
                .orElseThrow(() -> new EntityNotFoundException("Tv Show not found"));
    }

    public TvShowDTO findById(Long id) {
        return tvShowRepository.findById(id)
                .map(tvShowMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Tv Show not found "));
    }

    public MediaDTO update(Long id, MediaUpdateDTO mediaDTO) {

        TvShow entity = tvShowRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tv Show not found"));

        TvShow newMovie = mediaMapper.toTvShowEntityPatch(mediaDTO);

        if (entity != null) {
            if (newMovie.getTitle() != null && !newMovie.getTitle().equals(entity.getTitle())) entity.setTitle(newMovie.getTitle());
            if (newMovie.getBackdrop() != null && !newMovie.getBackdrop().equals(entity.getBackdrop())) entity.setBackdrop(newMovie.getBackdrop());
            if (newMovie.getCover() != null && !newMovie.getCover().equals(entity.getCover())) entity.setCover(newMovie.getCover());
            if (newMovie.getLogo() != null && !newMovie.getLogo().equals(entity.getLogo())) entity.setLogo(newMovie.getLogo());
            if (newMovie.getLongDescription() != null && !newMovie.getLongDescription().equals(entity.getLongDescription())) entity.setLongDescription(newMovie.getLongDescription());
            if (newMovie.getShortDescription() != null && !newMovie.getShortDescription().equals(entity.getShortDescription())) entity.setShortDescription(newMovie.getShortDescription());

            return mediaMapper.toTvShowDTO(tvShowRepository.save(entity));
        }

        return null;
    }

    public String delete(Long id) {
        try {
            tvShowRepository.findById(id)
                    .map(movie -> {
                        tvShowRepository.deleteById(id);
                        return "Tv Show deleted";
                    })
                    .orElseThrow(() -> new EntityNotFoundException("Tv Show not found"));
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Violação de integridade");
        }

        return "";
    }


    public MediaDTO create(MediaCreateDTO tvShow) {
        log.info("Creating an Tv Show with id " + tvShow.getId());
        return mediaMapper.toTvShowDTO(tvShowRepository.save(mediaMapper.toTvShowEntity(tvShow)));
    }

}