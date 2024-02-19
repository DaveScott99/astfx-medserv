package com.daverj.media.dto.response;

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
public class TvShowDTO {

    private Long id;
    private String title;
    private String trailer;
    private String logo;
    private String backdrop;
    private String overview;
    private Integer releaseYear;
    private Set<GenreDTO> genres = new HashSet<>();
    private List<SeasonDTO> seasons = new ArrayList<>();

    public TvShowDTO(TvShow tvShow) {
        id = tvShow.getId();
        title = tvShow.getTitle();
        trailer = tvShow.getTrailer();
        overview = tvShow.getOverview();
        releaseYear = tvShow.getReleaseYear();
        tvShow.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
        tvShow.getSeasons().forEach(season -> getSeasons().add(new SeasonDTO(season)));
    }

}
