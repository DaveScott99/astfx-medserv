package com.daverj.media.repository;

import com.daverj.media.model.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {
}
