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
    private String cover;
    private String logo;

    public MediaMinDTO(Media media) {
        id = media.getId();
        title = media.getTitle();
        cover = media.getCover();
        logo = media.getLogo();
    }

    public MediaMinDTO(TvShow tvShow) {
        id = tvShow.getId();
        title = tvShow.getTitle();
        cover = tvShow.getCover();
        logo = tvShow.getLogo();
    }

    public MediaMinDTO(Movie movie) {
        id = movie.getId();
        title = movie.getTitle();
        cover = movie.getCover();
        logo = movie.getLogo();
    }
}