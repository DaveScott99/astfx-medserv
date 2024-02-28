package com.daverj.media.repository;

import com.daverj.media.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    Optional<Media> findByTitle(String title);

    @Query(value = "INSERT INTO astfx_media_db.media_genre VALUES (:mediaId, :genreId)", nativeQuery = true)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    int addGenre(@Param("mediaId") Long mediaId, @Param("genreId") Long genreId);

    @Query(value = "DELETE FROM astfx_media_db.media_genre WHERE media_id = :mediaId && genre_id = :genreId", nativeQuery = true)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    int removeGenre(@Param("mediaId") Long mediaId, @Param("genreId") Long genreId);

    @Query(value = "UPDATE media SET is_active = 0 WHERE id=:mediaId", nativeQuery = true)
    @Modifying
    int disable(@Param("mediaId") Long mediaId);

    @Query(value = "UPDATE media SET is_active = 1 WHERE id=:mediaId", nativeQuery = true)
    @Modifying
    int active(@Param("mediaId") Long mediaId);


}
