package com.daverj.media.dto.response;

import com.daverj.media.model.Genre;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GenreDTO {

    private Long id;
    private String name;

    public GenreDTO(Genre entity) {
        id = entity.getId();
        name = entity.getName();
    }

}