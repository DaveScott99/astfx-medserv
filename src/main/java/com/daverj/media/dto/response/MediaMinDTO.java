package com.daverj.media.dto.response;

import com.daverj.media.model.Art;
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
    private String title;
    private Integer releaseYear;
    private boolean isActive;
    private ArtDTO poster;

    public MediaMinDTO(Media media) {
        id = media.getId();
        title = media.getTitle();
        releaseYear = media.getReleaseYear();
        isActive = media.isActive();
        poster = new ArtDTO(media.getLogos()
                .stream().filter(art -> art.getType().contains("poster"))
                .filter(Art::isSelected)
                .findFirst().orElseGet(() -> new Art("Standard logo", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/330px-No-Image-Placeholder.svg.png?20200912122019", "poster")));
    }

}