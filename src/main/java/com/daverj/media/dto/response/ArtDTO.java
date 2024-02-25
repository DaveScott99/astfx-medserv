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
    private String filePath;
    private String type;
    private Double aspectRatio;

    public ArtDTO(Art entity) {
        id = entity.getId();
        name = entity.getName();
        filePath = entity.getFilePath();
        type = entity.getType();
        aspectRatio = entity.getAspectRatio();
    }
}
