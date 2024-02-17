package com.daverj.media.dto.response;

import com.daverj.media.model.Art;
import com.daverj.media.model.Media;
import com.daverj.media.model.Movie;
import com.daverj.media.model.TvShow;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaDTO {

    private Long id;
    private String title;
    private Integer releaseYear;
    private List<GenreDTO> genres = new ArrayList<>();
    private boolean isActive;
    private ArtDTO poster;

    public MediaDTO(Media media) {
        id = media.getId();
        title = media.getTitle();
        releaseYear = media.getReleaseYear();
        isActive = media.isActive();
        media.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
        poster = new ArtDTO(media.getLogos()
                .stream().filter(art -> art.getType().contains("poster"))
                .filter(Art::isSelected)
                .findFirst().orElseGet(() -> new Art("Standard logo", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/330px-No-Image-Placeholder.svg.png?20200912122019", "poster")));
    }

    public MediaDTO(TvShow tvShow) {
        id = tvShow.getId();
        title = tvShow.getTitle();
        releaseYear = tvShow.getReleaseYear();
        isActive = tvShow.isActive();
        tvShow.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
        poster = new ArtDTO(tvShow.getLogos()
                .stream().filter(art -> art.getType().contains("poster"))
                .filter(Art::isSelected)
                .findFirst().orElseGet(() -> new Art("Standard logo", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/330px-No-Image-Placeholder.svg.png?20200912122019", "poster")));
    }

    public MediaDTO(Movie movie) {
        id = movie.getId();
        title = movie.getTitle();
        releaseYear = movie.getReleaseYear();
        isActive = movie.isActive();
        movie.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
        poster = new ArtDTO(movie.getLogos()
                    .stream().filter(art -> art.getType().contains("poster"))
                    .filter(Art::isSelected)
                    .findFirst().orElseGet(() -> new Art("Standard logo", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/330px-No-Image-Placeholder.svg.png?20200912122019", "poster")));
    }
}