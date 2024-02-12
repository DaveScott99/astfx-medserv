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
    private String poster;
    private String logo;
    private String overview;
    private Integer releaseYear;
    private Set<GenreDTO> genres = new HashSet<>();

    public MediaDTO(Media media) {
        id = media.getId();
        title = media.getTitle();
        trailerUrl = media.getTrailer();
        poster = media.getPoster();
        logo = media.getLogo();
        overview = media.getOverview();
        releaseYear = media.getReleaseYear();
        media.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
    }

    public MediaDTO(TvShow tvShow) {
        id = tvShow.getId();
        title = tvShow.getTitle();
        trailerUrl = tvShow.getTrailer();
        poster = tvShow.getPoster();
        logo = tvShow.getLogo();
        overview = tvShow.getOverview();
        releaseYear = tvShow.getReleaseYear();
        tvShow.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
    }

    public MediaDTO(Movie movie) {
        id = movie.getId();
        title = movie.getTitle();
        trailerUrl = movie.getTrailer();
        poster = movie.getPoster();
        logo = movie.getLogo();
        overview = movie.getOverview();
        releaseYear = movie.getReleaseYear();
        movie.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
    }
}