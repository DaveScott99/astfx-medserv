package com.daverj.media.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ImageTmdbDTO {
    private Double aspect_ratio;
    private Integer height;
    private Integer width;
    private String file_path;

    public ImageTmdbDTO(ImageTmdbDTO imageTmdbDTO) {
        aspect_ratio = imageTmdbDTO.getAspect_ratio();
        height = imageTmdbDTO.getHeight();
        width = imageTmdbDTO.getWidth();
        file_path = imageTmdbDTO.getFile_path();
    }

}
