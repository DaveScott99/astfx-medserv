package com.daverj.media.dto.response;

import com.daverj.media.model.Art;
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
    private String overview;
    private Integer releaseYear;
    private Set<GenreDTO> genres = new HashSet<>();

    private ArtDTO logo;
    private ArtDTO poster;

    public MediaDTO(Media media) {
        id = media.getId();
        title = media.getTitle();
        trailerUrl = media.getTrailer();
        overview = media.getOverview();
        releaseYear = media.getReleaseYear();
        media.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
        logo = new ArtDTO(media.getLogos()
                .stream()
                .filter(art -> art.getType().contains("logo"))
                .filter(Art::isSelected)
                .findFirst().get());
        poster = new ArtDTO(media.getLogos()
                .stream().filter(art -> art.getType().contains("poster"))
                .filter(Art::isSelected)
                .findFirst().get());
    }

    public MediaDTO(TvShow tvShow) {
        id = tvShow.getId();
        title = tvShow.getTitle();
        trailerUrl = tvShow.getTrailer();
        overview = tvShow.getOverview();
        releaseYear = tvShow.getReleaseYear();
        tvShow.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
        logo = new ArtDTO(tvShow.getLogos()
                .stream()
                .filter(art -> art.getType().contains("logo"))
                .filter(Art::isSelected)
                .findFirst().get());
        poster = new ArtDTO(tvShow.getLogos()
                .stream().filter(art -> art.getType().contains("poster"))
                .filter(Art::isSelected)
                .findFirst().get());
    }

    public MediaDTO(Movie movie) {
        id = movie.getId();
        title = movie.getTitle();
        trailerUrl = movie.getTrailer();
        overview = movie.getOverview();
        releaseYear = movie.getReleaseYear();
        movie.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
        logo = new ArtDTO(movie.getLogos()
                .stream()
                .filter(art -> art.getType().contains("logo"))
                .filter(Art::isSelected)
                .findFirst().get());
        poster = new ArtDTO(movie.getLogos()
                    .stream().filter(art -> art.getType().contains("poster"))
                    .filter(Art::isSelected)
                    .findFirst().get());
    }
}