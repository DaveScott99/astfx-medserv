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
    private String file;
    private String type;

    @CreationTimestamp
    private Instant createdAt;

    private boolean isSelected;

    @ManyToOne
    @JoinColumn(name = "media_id", nullable = false)
    private Media media;

}
