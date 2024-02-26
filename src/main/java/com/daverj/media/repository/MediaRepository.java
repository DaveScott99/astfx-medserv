package com.daverj.media.repository;

import com.daverj.media.model.Media;
import com.daverj.media.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    Optional<Media> findByTitle(String title);

}
