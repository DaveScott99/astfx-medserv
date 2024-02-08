package com.daverj.media.dto.request;

import com.daverj.media.model.Genre;
import com.daverj.media.model.Media;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

@JsonPropertyOrder(value = {"id", "title", "trailer", "cover", "logo", "duration", "longDescription", "shortDescription",
         "country", "year", "genres", "backdrop"})
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

    @NotBlank(message = "The field 'cover' is mandatory")
    @JsonProperty(value = "cover")
    private String cover;

    @NotBlank(message = "The field 'logo' is mandatory")
    @JsonProperty(value = "logo")
    private String logo;

    @NotBlank(message = "The field 'backdrop' is mandatory")
    @JsonProperty(value = "backdrop")
    private String backdrop;

    @NotBlank(message = "The field 'duration' is mandatory")
    @JsonProperty(value = "duration")
    private String duration;

    @NotBlank(message = "The field 'longDescription' is mandatory")
    @JsonProperty(value = "longDescription")
    private String longDescription;

    @NotBlank(message = "The field 'shortDescription' is mandatory")
    @JsonProperty(value = "shortDescription")
    private String shortDescription;

    @NotBlank(message = "The field 'country' is mandatory")
    @JsonProperty(value = "country")
    private String country;

    @NotBlank(message = "The field 'year' is mandatory")
    @JsonProperty(value = "year")
    private Integer year;

    @NotBlank(message = "The field 'genres' is mandatory")
    @JsonProperty(value = "genres")
    private Set<Genre> genres;

    public MediaCreateDTO(Media media) {
        id = media.getId();
        title = media.getTitle();
        trailer = media.getTrailer();
        cover = media.getCover();
        logo = media.getLogo();
        duration = media.getDuration();
        longDescription = media.getLongDescription();
        shortDescription = media.getShortDescription();
        country = media.getCountry();
        year = media.getYear();
        media.getGenres().forEach(genre -> genres.add(genre));
    }

}