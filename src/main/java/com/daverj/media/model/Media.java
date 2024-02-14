package com.daverj.media.model;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_MEDIA")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
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

    private boolean isActive = true;

    @Column(nullable = false)
    private boolean isAdult;

    private Integer rating;

    private String sourceFolder;

    @ManyToMany
    @JoinTable(name = "media_genre",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

}