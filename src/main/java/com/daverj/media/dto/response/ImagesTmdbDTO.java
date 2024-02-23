package com.daverj.media.dto.response;

import com.daverj.media.ImageRecord;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ImagesTmdbDTO {

    private List<ImageRecord> backdrops;
    private List<ImageRecord> logos;
    private List<ImageRecord> posters;

}
