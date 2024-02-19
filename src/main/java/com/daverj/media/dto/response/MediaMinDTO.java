package com.daverj.media.dto.response;

import com.daverj.media.model.Media;
import com.daverj.media.model.Movie;
import com.daverj.media.model.TvShow;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaMinDTO {

    private Long id;
    private Long idTMDB;
    private String title;

    public MediaMinDTO(Media media) {
        id = media.getId();
        idTMDB = media.getIdTMDB();
        title = media.getTitle();
    }

    public MediaMinDTO(TvShow tvShow) {
        id = tvShow.getId();
        idTMDB = tvShow.getIdTMDB();
        title = tvShow.getTitle();
    }

    public MediaMinDTO(Movie movie) {
        id = movie.getId();
        idTMDB = movie.getIdTMDB();
        title = movie.getTitle();
    }
}