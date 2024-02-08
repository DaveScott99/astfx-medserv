package com.daverj.media.dto.response;

import com.daverj.media.model.Genre;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TimelineDTO {

    private Long id;
    private String name;
    private List<MediaMinDTO> medias = new ArrayList<>();

    public TimelineDTO(Genre genre) {
        id = genre.getId();
        name = genre.getName();
        genre.getMedias().forEach(media -> getMedias().add(new MediaMinDTO(media)));
    }

}