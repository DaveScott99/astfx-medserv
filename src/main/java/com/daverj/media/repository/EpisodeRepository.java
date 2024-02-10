package com.daverj.media.repository;

import com.daverj.media.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    Optional<Episode> findByTitle(String title);
}
