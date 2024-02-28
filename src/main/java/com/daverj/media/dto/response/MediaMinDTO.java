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
                .findFirst().orElseGet(() -> new Image("Standard logo", "https://placehold.co/300x450?text=Empty+Poster&font=roboto", "poster")));
    }

}