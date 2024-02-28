package com.daverj.media.dto.response;

import com.daverj.media.model.Image;
import com.daverj.media.model.Media;
import lombok.*;

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
    private ImageDTO poster;

    public MediaMinDTO(Media media) {
        id = media.getId();
        title = media.getTitle();
        releaseYear = media.getReleaseYear();
        isActive = media.isActive();
        poster = new ImageDTO(media.getLogos()
                .stream().filter(art -> art.getType().contains("poster"))
                .filter(Image::isSelected)
                .findFirst().orElseGet(() -> new Image("Standard logo", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/No-Image-Placeholder.svg/330px-No-Image-Placeholder.svg.png?20200912122019", "poster")));
    }

}