package com.daverj.media.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@DiscriminatorValue("tv-show")
public class TvShow extends Media {

    @JsonIgnore
    @OneToMany(mappedBy = "tvShow")
    private List<Season> seasons = new ArrayList<>();

    public TvShow(Media media) {
        this.setTitle(media.getTitle());
        this.setOverview(media.getOverview());
        this.setTagline(media.getTagline());
        this.setIdTMDB(media.getIdTMDB());
        this.setReleaseYear(media.getReleaseYear());
        this.setAdult(media.isAdult());
        this.setRuntime(getRuntime());
    }

}