package com.daverj.media.dto.request;

import com.daverj.media.model.TvShow;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@JsonPropertyOrder(value = {"tvShow"})
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SeasonCreateDTO {

    @NotBlank(message = "The field 'tvShow' is mandatory")
    @JsonProperty(value = "tvShow")
    private TvShow tvShow;

}
