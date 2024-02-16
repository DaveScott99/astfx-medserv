package com.daverj.media.dto.response;

import com.daverj.media.model.Art;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ArtDTO {
    private Long id;
    private String name;
    private String file;
    private String type;
    private boolean isSelected;

    public ArtDTO(Art entity) {
        id = entity.getId();
        name = entity.getName();
        file = entity.getFile();
        type = entity.getType();
        isSelected = entity.isSelected();
    }
}
