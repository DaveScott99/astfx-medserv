package com.daverj.media.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@JsonPropertyOrder(value = {"title", "overview", "tagline"})
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaUpdateDTO {

    @JsonProperty(value = "title")
    private String title;

    @JsonProperty(value = "overview")
    private String overview;

    @JsonProperty(value = "tagline")
    private String tagline;

}
