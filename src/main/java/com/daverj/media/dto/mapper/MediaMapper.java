package com.daverj.media.dto.mapper;

import com.daverj.media.dto.request.MediaCreateDTO;
import com.daverj.media.dto.request.MediaUpdateDTO;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.model.Movie;
import com.daverj.media.model.TvShow;
import org.springframework.stereotype.Component;

@Component
public class MediaMapper {

    public MediaDTO toMovieDTO(Movie movie) {
        if (movie == null)
            return null;

        return new MediaDTO(movie);
    }

    public MediaDTO toTvShowDTO(TvShow tvShow) {
        if (tvShow == null)
            return null;

        return new MediaDTO(tvShow);
    }

    public Movie toMovieEntity(MediaCreateDTO mediaDTO) {

        if (mediaDTO == null)
            return null;

        Movie entity = new Movie();

        entity.setId(mediaDTO.getId());
        entity.setTitle(mediaDTO.getTitle());
        entity.setBackdrop(mediaDTO.getBackdrop());
        entity.setLogo(mediaDTO.getLogo());
        entity.setPoster(mediaDTO.getPoster());
        entity.setOverview(mediaDTO.getOverview());
        entity.setDescription(mediaDTO.getDescription());
        entity.setRuntime(mediaDTO.getRuntime());
        entity.setReleaseYear(mediaDTO.getReleaseYear());
        entity.setTrailer(mediaDTO.getTrailer());
        mediaDTO.getGenres().forEach(x -> entity.getGenres().add(x));

        return entity;

    }

    public TvShow toTvShowEntity(MediaCreateDTO mediaDTO) {

        if (mediaDTO == null)
            return null;

        TvShow entity = new TvShow();

        entity.setId(mediaDTO.getId());
        entity.setTitle(mediaDTO.getTitle());
        entity.setBackdrop(mediaDTO.getBackdrop());
        entity.setLogo(mediaDTO.getLogo());
        entity.setPoster(mediaDTO.getPoster());
        entity.setOverview(mediaDTO.getOverview());
        entity.setDescription(mediaDTO.getDescription());
        entity.setRuntime(mediaDTO.getRuntime());
        entity.setReleaseYear(mediaDTO.getReleaseYear());
        entity.setTrailer(mediaDTO.getTrailer());
        mediaDTO.getGenres().forEach(x -> entity.getGenres().add(x));

        return entity;

    }

    public Movie toMovieEntityPatch(MediaUpdateDTO dto) {

        if (dto == null)
            return null;

        Movie entity = new Movie();

        entity.setTitle(dto.getTitle());
        entity.setBackdrop(dto.getBackdrop());
        entity.setPoster(dto.getPoster());
        entity.setLogo(dto.getLogo());
        entity.setOverview(dto.getOverview());
        entity.setDescription(dto.getDescription());

        return entity;

    }

    public TvShow toTvShowEntityPatch(MediaUpdateDTO dto) {

        if (dto == null)
            return null;

        TvShow entity = new TvShow();

        entity.setTitle(dto.getTitle());
        entity.setBackdrop(dto.getBackdrop());
        entity.setPoster(dto.getPoster());
        entity.setLogo(dto.getLogo());
        entity.setOverview(dto.getOverview());
        entity.setDescription(dto.getDescription());

        return entity;

    }

}