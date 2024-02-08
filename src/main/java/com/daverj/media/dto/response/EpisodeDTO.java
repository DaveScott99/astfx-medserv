package com.daverj.media.dto.response;

import com.daverj.media.model.Episode;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EpisodeDTO {

    private Long id;
    private String title;
    private String duration;
    private String description;
    private String backdrop;

    public EpisodeDTO(Episode entity) {
        id = entity.getId();
        title = entity.getTitle();
        duration = entity.getDuration();
        description = entity.getDescription();
        backdrop = entity.getBackdrop();
    }

}
