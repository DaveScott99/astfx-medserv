package com.daverj.media.dto.mapper;

import com.daverj.media.dto.request.EpisodeCreateDTO;
import com.daverj.media.dto.response.EpisodeDTO;
import com.daverj.media.model.Episode;
import org.springframework.stereotype.Component;

@Component
public class EpisodeMapper {

    public EpisodeDTO toDTO (Episode entity) {
        if (entity == null)
            return null;

        return new EpisodeDTO(entity);
    }

    public Episode toEntity (EpisodeCreateDTO dto) {

        if (dto == null)
            return null;

        Episode entity = new Episode();

        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setDuration(dto.getDuration());
        entity.setBackdrop(dto.getBackdrop());
        entity.setSeason(dto.getSeason());

        return entity;
    }
}
