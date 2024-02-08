package com.daverj.media.dto.response;

import com.daverj.media.model.Media;
import com.daverj.media.model.Movie;
import com.daverj.media.model.TvShow;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaDTO {

    private Long id;
    private String title;
    private String trailerUrl;
    private String cover;
    private String logo;
    private String longDescription;
    private Integer year;
    private Set<GenreDTO> genres = new HashSet<>();

    public MediaDTO(Media media) {
        id = media.getId();
        title = media.getTitle();
        trailerUrl = media.getTrailer();
        cover = media.getCover();
        logo = media.getLogo();
        longDescription = media.getLongDescription();
        year = media.getYear();
        media.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
    }

    public MediaDTO(TvShow tvShow) {
        id = tvShow.getId();
        title = tvShow.getTitle();
        trailerUrl = tvShow.getTrailer();
        cover = tvShow.getCover();
        logo = tvShow.getLogo();
        longDescription = tvShow.getLongDescription();
        year = tvShow.getYear();
        tvShow.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
    }

    public MediaDTO(Movie movie) {
        id = movie.getId();
        title = movie.getTitle();
        trailerUrl = movie.getTrailer();
        cover = movie.getCover();
        logo = movie.getLogo();
        longDescription = movie.getLongDescription();
        year = movie.getYear();
        movie.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
    }
}