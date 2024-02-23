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
    private Long idTMDB;
    private String title;
    private Integer releaseYear;
    private Integer runtime;
    private String overview;
    private String tagline;
    private List<GenreDTO> genres = new ArrayList<>();
    private boolean isActive;
    private boolean isAdult;
    private ArtDTO poster;
    private ArtDTO backdrop;
    private ArtDTO logo;

    public MediaDTO(Media media) {
        id = media.getId();
        idTMDB = media.getIdTMDB();
        title = media.getTitle();
        releaseYear = media.getReleaseYear();
        runtime = media.getRuntime();
        overview = media.getOverview();
        tagline = media.getTagline();
        isActive = media.isActive();
        isAdult = media.isAdult();
        media.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
        poster = new ArtDTO(media.getLogos()
                .stream().filter(art -> art.getType().contains("poster"))
                .filter(Art::isSelected)
                .findFirst().orElseGet(() -> new Art("Standard logo", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/330px-No-Image-Placeholder.svg.png?20200912122019", "poster")));
        backdrop = new ArtDTO(media.getBackdrops()
                .stream().filter(art -> art.getType().contains("backdrop"))
                .filter(Art::isSelected)
                .findFirst().orElseGet(() -> new Art("Standard backdrop", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/330px-No-Image-Placeholder.svg.png?20200912122019", "backdrop")));
        logo = new ArtDTO(media.getBackdrops()
                .stream().filter(art -> art.getType().contains("logo"))
                .filter(Art::isSelected)
                .findFirst().orElseGet(() -> new Art("Standard logo", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/330px-No-Image-Placeholder.svg.png?20200912122019", "logo")));
    }
}