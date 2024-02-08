package com.daverj.media.dto.request;

import com.daverj.media.model.Episode;
import com.daverj.media.model.Season;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@JsonPropertyOrder(value = {"title", "duration", "description", "season", "backdrop"})
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EpisodeCreateDTO {

    @NotBlank(message = "The field 'title' is mandatory")
    @JsonProperty(value = "title")
    private String title;

    @NotBlank(message = "The field 'duration' is mandatory")
    @JsonProperty(value = "duration")
    private String duration;

    @NotBlank(message = "The field 'description' is mandatory")
    @JsonProperty(value = "description")
    private String description;

    @NotBlank(message = "The field 'backdrop' is mandatory")
    @JsonProperty(value = "backdrop")
    private String backdrop;

    @NotBlank(message = "The field 'season' is mandatory")
    @JsonProperty(value = "season")
    private Season season;

}
