package com.daverj.media.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_MEDIA")
@EqualsAndHashCode
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idTMDB;

    @Column(nullable = false)
    private String title;

    private String trailer;

    private String poster;

    private String logo;

    private String backdrop;

    @Column(nullable = false)
    private String runtime;

    @Column(nullable = false, length = 512)
    private String overview;

    @Column(nullable = false)
    private String tagline;

    @Column(nullable = false)
    private Integer releaseYear;

    @CreationTimestamp
    private Instant createdAt;

    @CreationTimestamp
    private Instant updatedAt;

    private boolean isActive = true;

    @Column(nullable = false)
    private boolean isAdult;

    private Integer rating;

    private String sourceFolder;

    @OneToMany(mappedBy = "media")
    @JsonIgnore
    private Set<Art> posters = new HashSet<>();

    @OneToMany(mappedBy = "media")
    @JsonIgnore
    private Set<Art> logos = new HashSet<>();

    @OneToMany(mappedBy = "media")
    @JsonIgnore
    private Set<Art> backdrops = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "media_genre",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres = new ArrayList<>();

}