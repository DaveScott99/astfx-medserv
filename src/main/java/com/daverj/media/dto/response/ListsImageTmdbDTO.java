package com.daverj.media.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ListsImageTmdbDTO {

    private List<ImageTmdbDTO> backdrops;
    private List<ImageTmdbDTO> logos;
    private List<ImageTmdbDTO> posters;

}
