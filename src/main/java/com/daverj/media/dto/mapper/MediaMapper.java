package com.daverj.media.dto.mapper;

import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.request.MediaUpdateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.response.MediaMinDTO;
import com.daverj.media.model.Media;
import com.daverj.media.model.Movie;
import com.daverj.media.model.TvShow;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper {

    public MediaDTO toDTO(Media media) {
        if (media == null)
            return null;

        return new MediaDTO(media);
    }

    public MediaMinDTO toMinDTO (Media entity) {
        if (entity == null)
            return null;
        return new MediaMinDTO(entity);

    }

    public Media toEntity(MediaCreateDTO mediaCreate) {

        if (mediaCreate == null)
            return null;

        Media entity = new Media();

        entity.setIdTMDB(mediaCreate.getIdTMDB());
        entity.setTitle(mediaCreate.getTitle());
        entity.setOverview(mediaCreate.getOverview());
        entity.setTagline(mediaCreate.getTagline());
        entity.setRuntime(mediaCreate.getRuntime());
        entity.setReleaseYear(mediaCreate.getReleaseYear());
        entity.setAdult((mediaCreate.isAdult()));

        return entity;

    }


    public Movie toMovieEntityPatch(MediaUpdateDTO dto) {

        if (dto == null)
            return null;

        Movie entity = new Movie();

        entity.setTitle(dto.getTitle());
        entity.setOverview(dto.getOverview());
        entity.setTagline(dto.getTagline());

        return entity;

    }

    public TvShow toTvShowEntityPatch(MediaUpdateDTO dto) {

        if (dto == null)
            return null;

        TvShow entity = new TvShow();

        entity.setTitle(dto.getTitle());
        entity.setOverview(dto.getOverview());
        entity.setTagline(dto.getTagline());

        return entity;

    }

}