package com.daverj.media.dto.response;

import com.daverj.media.model.Image;
import com.daverj.media.model.Media;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MediaDTO {

    private Long id;
    private Long idTMDB;
    private String title;
    private Integer releaseYear;
    private Integer runtime;
    private String overview;
    private String tagline;
    private List<GenreDTO> genres = new ArrayList<>();
    private boolean isActive;
    private boolean isAdult;
    private ImageDTO poster;
    private ImageDTO backdrop;
    private ImageDTO logo;

    public MediaDTO(Media media) {
        id = media.getId();
        idTMDB = media.getIdTMDB();
        title = media.getTitle();
        releaseYear = media.getReleaseYear();
        runtime = media.getRuntime();
        overview = media.getOverview();
        tagline = media.getTagline();
        isActive = media.isActive();
        isAdult = media.isAdult();
        media.getGenres().forEach(genre -> getGenres().add(new GenreDTO(genre)));
        poster = new ImageDTO(media.getLogos()
                .stream().filter(art -> art.getType().contains("poster"))
                .filter(Image::isSelected)
                .findFirst().orElseGet(() -> new Image("Standard logo", "https://placehold.co/300x450?text=Empty+Poster&font=roboto", "poster")));
        backdrop = new ImageDTO(media.getBackdrops()
                .stream().filter(art -> art.getType().contains("backdrop"))
                .filter(Image::isSelected)
                .findFirst().orElseGet(() -> new Image("Standard backdrop", "https://placehold.co/1920x1080?text=Empty+Backdrop&font=roboto", "backdrop")));
        logo = new ImageDTO(media.getBackdrops()
                .stream().filter(art -> art.getType().contains("logo"))
                .filter(Image::isSelected)
                .findFirst().orElseGet(() -> new Image("Standard logo", "https://placehold.co/400x250?text=Empty+Logo&font=roboto", "logo")));
    }
}