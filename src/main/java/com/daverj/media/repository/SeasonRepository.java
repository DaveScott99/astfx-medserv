package com.daverj.media.repository;

import com.daverj.media.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
}
