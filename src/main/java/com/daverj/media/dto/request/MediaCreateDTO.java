package com.daverj.media.dto.request;

import com.daverj.media.model.Genre;
import com.daverj.media.model.Media;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

@JsonPropertyOrder(value = {"id", "title", "trailer", "poster", "logo", "runtime", "overview", "description",
        "releaseYear", "genres", "poster"})
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaCreateDTO {

    private Long id;

    @NotBlank(message = "The field 'title' is mandatory")
    @JsonProperty(value = "title")
    private String title;

    @NotBlank(message = "The field 'trailer' is mandatory")
    @JsonProperty(value = "trailer")
    private String trailer;

    @NotBlank(message = "The field 'poster' is mandatory")
    @JsonProperty(value = "poster")
    private String poster;

    @NotBlank(message = "The field 'logo' is mandatory")
    @JsonProperty(value = "logo")
    private String logo;

    @NotBlank(message = "The field 'backdrop' is mandatory")
    @JsonProperty(value = "backdrop")
    private String backdrop;

    @NotBlank(message = "The field 'runtime' is mandatory")
    @JsonProperty(value = "runtime")
    private String runtime;

    @NotBlank(message = "The field 'overview' is mandatory")
    @JsonProperty(value = "overview")
    private String overview;

    @NotBlank(message = "The field 'description' is mandatory")
    @JsonProperty(value = "description")
    private String description;

    @NotBlank(message = "The field 'releaseYear' is mandatory")
    @JsonProperty(value = "releaseYear")
    private Integer releaseYear;

    @NotBlank(message = "The field 'genres' is mandatory")
    @JsonProperty(value = "genres")
    private List<Genre> genres;

    public MediaCreateDTO(Media media) {
        id = media.getId();
        title = media.getTitle();
        trailer = media.getTrailer();
        poster = media.getPoster();
        logo = media.getLogo();
        runtime = media.getRuntime();
        overview = media.getOverview();
        description = media.getDescription();
        releaseYear = media.getReleaseYear();
        media.getGenres().forEach(genre -> genres.add(genre));
    }

}