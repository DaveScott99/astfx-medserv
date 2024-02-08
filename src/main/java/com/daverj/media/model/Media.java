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
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String trailer;

    @Column(nullable = false)
    private String cover;

    @Column(nullable = false)
    private String logo;

    @Column(nullable = false)
    private String backdrop;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false, length = 512)
    private String longDescription;

    @Column(nullable = false)
    private String shortDescription;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Integer year;

    @CreationTimestamp
    private Instant createdAt;

    @ManyToMany
    @JoinTable(name = "media_genre",
            joinColumns = @JoinColumn(name = "media_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @NotNull(message = "The field 'genres' is mandatory")
    private Set<Genre> genres = new HashSet<>();

}