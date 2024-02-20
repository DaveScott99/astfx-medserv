package com.daverj.media.dto.request;

import com.daverj.media.model.Genre;
import com.daverj.media.model.Media;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

@JsonPropertyOrder(value = {"idTMDB", "title", "runtime", "overview", "tagline", "isAdult", "releaseYear"})
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaCreateDTO {

    @NotNull(message = "The field 'idTMDB' is required")
    @Min(1)
    @JsonProperty(value = "idTMDB")
    private Long idTMDB;

    @NotBlank(message = "The field 'title' is required")
    @JsonProperty(value = "title")
    private String title;

    @NotBlank(message = "The field 'runtime' is required")
    @JsonProperty(value = "runtime")
    private String runtime;

    @NotBlank(message = "The field 'overview' is required")
    @JsonProperty(value = "overview")
    private String overview;

    @NotBlank(message = "The field 'tagline' is required")
    @JsonProperty(value = "tagline")
    private String tagline;

    @NotNull(message = "The field 'releaseYear' is required")
    @Min(1)
    @JsonProperty(value = "releaseYear")
    private Integer releaseYear;

    @NotNull(message = "The field 'isAdult' is required")
    @JsonProperty(value = "isAdult")
    private boolean isAdult;

    public MediaCreateDTO(Media media) {
        idTMDB = media.getIdTMDB();
        title = media.getTitle();
        runtime = media.getRuntime();
        overview = media.getOverview();
        tagline = media.getTagline();
        releaseYear = media.getReleaseYear();
        isAdult = media.isAdult();
    }

}