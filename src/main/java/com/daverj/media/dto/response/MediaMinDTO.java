package com.daverj.media.dto.response;

import com.daverj.media.model.Media;
import com.daverj.media.model.Movie;
import com.daverj.media.model.TvShow;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaMinDTO {

    private Long id;
    private String title;
    private String poster;
    private String logo;

    public MediaMinDTO(Media media) {
        id = media.getId();
        title = media.getTitle();
        poster = media.getPoster();
        logo = media.getLogo();
    }

    public MediaMinDTO(TvShow tvShow) {
        id = tvShow.getId();
        title = tvShow.getTitle();
        poster = tvShow.getPoster();
        logo = tvShow.getLogo();
    }

    public MediaMinDTO(Movie movie) {
        id = movie.getId();
        title = movie.getTitle();
        poster = movie.getPoster();
        logo = movie.getLogo();
    }
}