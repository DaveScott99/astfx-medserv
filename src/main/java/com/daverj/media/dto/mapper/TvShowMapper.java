package com.daverj.media.dto.mapper;

import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.response.TvShowDTO;
import com.daverj.media.model.TvShow;
import org.springframework.stereotype.Component;

@Component
public class TvShowMapper {

    public TvShowDTO toDTO(TvShow tvShow) {
        if (tvShow == null)
            return null;

        return new TvShowDTO(tvShow);
    }
}
