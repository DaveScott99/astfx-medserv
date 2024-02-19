package com.daverj.media.service;

import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.request.MediaUpdateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.mapper.MediaMapper;
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

    public Page<MediaDTO> list(Pageable pageable) {
        log.info("Listing all Tv Shows");
        return tvShowRepository.findAll(pageable)
                .map(mediaMapper::toDTO);
    }

    public MediaDTO findByTitle(String title) {
        return tvShowRepository.findByTitle(title)
                .map(mediaMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Tv Show not found"));
    }

    public MediaDTO findById(Long id) {
        return tvShowRepository.findById(id)
                .map(mediaMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Tv Show not found "));
    }

    public MediaDTO update(Long id, MediaUpdateDTO mediaDTO) {

        TvShow entity = tvShowRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tv Show not found"));

        TvShow newTvShow = mediaMapper.toTvShowEntityPatch(mediaDTO);

        if (entity != null) {
            if (newTvShow.getTitle() != null && !newTvShow.getTitle().equals(entity.getTitle())) entity.setTitle(newTvShow.getTitle());
            if (newTvShow.getOverview() != null && !newTvShow.getOverview().equals(entity.getOverview())) entity.setOverview(newTvShow.getOverview());
            if (newTvShow.getTagline() != null && !newTvShow.getTagline().equals(entity.getTagline())) entity.setTagline(newTvShow.getTagline());

            return mediaMapper.toDTO(tvShowRepository.save(entity));
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


    public MediaDTO create(MediaCreateDTO tvShowCreate) {
        TvShow tvShow = new TvShow(mediaMapper.toEntity(tvShowCreate));
        return mediaMapper.toDTO(tvShowRepository.save(tvShow));
    }

}