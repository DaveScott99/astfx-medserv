package com.daverj.media.dto.mapper;

import com.daverj.media.dto.response.GenreDTO;
import com.daverj.media.dto.response.TimelineDTO;
import com.daverj.media.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreDTO toDTO(Genre genre) {
        if (genre == null)
            return null;

        return new GenreDTO(genre);
    }

    public TimelineDTO toDTOTimeline(Genre genre) {
        if (genre == null)
            return null;

        return new TimelineDTO(genre);
    }


}