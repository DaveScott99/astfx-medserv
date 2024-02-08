package com.daverj.media.dto.mapper;

import com.daverj.media.dto.request.SeasonCreateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.response.SeasonDTO;
import com.daverj.media.model.Movie;
import com.daverj.media.model.Season;
import org.springframework.stereotype.Component;

@Component
public class SeasonMapper {

    public SeasonDTO toDTO(Season entity) {
        if (entity == null)
            return null;

        return new SeasonDTO(entity);
    }

    public Season toEntity(SeasonCreateDTO dto) {
        if (dto == null)
            return null;

        Season entity = new Season();

        entity.setTvShow(dto.getTvShow());

        return entity;
    }
}
