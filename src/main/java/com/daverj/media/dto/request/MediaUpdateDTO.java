package com.daverj.media.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@JsonPropertyOrder(value = {"title", "cover", "logo", "longDescription", "shortDescription","backdrop"})
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaUpdateDTO {

    @NotBlank(message = "The field 'title' is mandatory")
    @JsonProperty(value = "title")
    private String title;

    @NotBlank(message = "The field 'cover' is mandatory")
    @JsonProperty(value = "cover")
    private String poster;

    @NotBlank(message = "The field 'logo' is mandatory")
    @JsonProperty(value = "logo")
    private String logo;

    @NotBlank(message = "The field 'backdrop' is mandatory")
    @JsonProperty(value = "backdrop")
    private String backdrop;

    @NotBlank(message = "The field 'overview' is mandatory")
    @JsonProperty(value = "overview")
    private String overview;

    @NotBlank(message = "The field 'description' is mandatory")
    @JsonProperty(value = "description")
    private String description;

}
