package com.daverj.media.model;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false, length = 512)
    private String description;

    @Column(nullable = false)
    private String backdrop;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

}