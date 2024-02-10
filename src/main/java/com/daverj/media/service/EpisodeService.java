package com.daverj.media.service;

import com.daverj.media.dto.mapper.EpisodeMapper;
import com.daverj.media.dto.request.EpisodeCreateDTO;
import com.daverj.media.dto.request.EpisodeUpdateDTO;
import com.daverj.media.dto.request.MediaUpdateDTO;
import com.daverj.media.dto.response.EpisodeDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.model.Episode;
import com.daverj.media.model.TvShow;
import com.daverj.media.repository.EpisodeRepository;
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
public class EpisodeService {

    private final EpisodeRepository episodeRepository;
    private final EpisodeMapper episodeMapper;

    public Page<EpisodeDTO> list(Pageable pageable) {
        log.info("Listing all episodes");
        return episodeRepository.findAll(pageable)
                .map(episodeMapper::toDTO);
    }

    public EpisodeDTO findById(Long id) {
        return episodeRepository.findById(id)
                .map(episodeMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Episode note found"));
    }

    public EpisodeDTO findByTitle(String title) {
        return episodeRepository.findByTitle(title)
                .map(episodeMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Episode note found"));
    }

    public EpisodeDTO update(Long id, EpisodeUpdateDTO episodeDTO) {

        Episode entity = episodeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Episode not found"));

        Episode newEpisode = episodeMapper.toEntityPatch(episodeDTO);

        if (entity != null) {
            if (newEpisode.getTitle() != null && !newEpisode.getTitle().equals(entity.getTitle())) entity.setTitle(newEpisode.getTitle());
            if (newEpisode.getBackdrop() != null && !newEpisode.getBackdrop().equals(entity.getBackdrop())) entity.setBackdrop(newEpisode.getBackdrop());
            if (newEpisode.getDescription() != null && !newEpisode.getDescription().equals(entity.getDescription())) entity.setDescription(newEpisode.getDescription());

            return episodeMapper.toDTO(episodeRepository.save(entity));
        }

        return null;
    }

    public String delete(Long id) {
        try {
            episodeRepository.findById(id)
                    .map(movie -> {
                        episodeRepository.deleteById(id);
                        return "Episode deleted";
                    })
                    .orElseThrow(() -> new EntityNotFoundException("Episode not found"));
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Violação de integridade");
        }

        return "";
    }


    public EpisodeDTO create(EpisodeCreateDTO episode) {
        log.info("Creating an Episode for some Tv Show");
        return episodeMapper.toDTO(episodeRepository.save(episodeMapper.toEntity(episode)));
    }

}