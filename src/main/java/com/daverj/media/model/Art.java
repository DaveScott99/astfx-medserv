package com.daverj.media.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Art {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String filePath;
    private String type;
    private Double aspectRatio;
    private Integer height;
    private Integer width;

    @CreationTimestamp
    private Instant createdAt;

    private boolean isSelected = false;

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

    public Art(String name, String file, String type) {
        this.name = name;
        this.filePath = file;
        this.type = type;
    }

    public Art(String name, String filePath, String type, Double aspectRatio, Integer height, Integer width, Media media) {
        this.name = name;
        this.filePath = filePath;
        this.type = type;
        this.aspectRatio = aspectRatio;
        this.height = height;
        this.width = width;
        this.media = media;
    }
}
