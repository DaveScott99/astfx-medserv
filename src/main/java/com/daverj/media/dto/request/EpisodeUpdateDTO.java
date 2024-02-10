package com.daverj.media.dto.request;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonPropertyOrder(value = {"title", "backdrop", "description"})
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EpisodeUpdateDTO {

    private String title;
    private String backdrop;
    private String description;

}
