package com.daverj.media.repository;

import com.daverj.media.model.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {

    Set<Art> findByMediaId(Long id);

}
