package com.daverj.media.dto.response;

import com.daverj.media.model.Image;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ImageDTO {
    private Long id;
    private String name;
    private String filePath;
    private String type;
    private Double aspectRatio;

    public ImageDTO(Image entity) {
        id = entity.getId();
        name = entity.getName();
        filePath = entity.getFilePath();
        type = entity.getType();
        aspectRatio = entity.getAspectRatio();
    }
}
