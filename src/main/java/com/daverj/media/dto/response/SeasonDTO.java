package com.daverj.media.dto.response;

import com.daverj.media.model.Episode;
import com.daverj.media.model.Season;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SeasonDTO {

    @EqualsAndHashCode.Exclude
    private Long id;
    private Set<EpisodeDTO> episodes = new HashSet<>();

    public SeasonDTO(Season entity) {
        id = entity.getId();
        entity.getEpisodes().forEach(x -> episodes.add(new EpisodeDTO(x)));
    }

}
