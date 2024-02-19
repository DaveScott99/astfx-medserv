package com.daverj.media.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@DiscriminatorValue("movie")
public class Movie extends Media {

    public Movie(Media media) {
        this.setTitle(media.getTitle());
        this.setOverview(media.getOverview());
        this.setTagline(media.getTagline());
        this.setIdTMDB(media.getIdTMDB());
        this.setReleaseYear(media.getReleaseYear());
        this.setAdult(media.isAdult());
        this.setRuntime(media.getRuntime());
    }

}