package com.daverj.media.repository;

import com.daverj.media.model.Art;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {

    Set<Art> findByMediaId(Long id);


    @Query(value = "CALL select_logo(:mediaId, :logoId) ", nativeQuery = true)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    int selectLogo(Long mediaId, Long logoId);

    @Query(value = "CALL select_poster(:mediaId, :posterId) ", nativeQuery = true)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    int selectPoster(Long mediaId, Long posterId);

    @Query(value = "CALL select_backdrop(:mediaId, :backdropId) ", nativeQuery = true)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    int selectBackdrop(Long mediaId, Long backdropId);

}
