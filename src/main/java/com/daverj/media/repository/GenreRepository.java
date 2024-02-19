package com.daverj.media.repository;

import com.daverj.media.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query(value = "SELECT\n" +
            "\tgenre.id,\n" +
            "\tgenre.name\n" +
            "FROM genre\n" +
            "LEFT OUTER JOIN media_genre ON media_genre.genre_id = genre.id\n" +
            "LEFT OUTER JOIN media ON media.id = media_genre.media_id\n" +
            "WHERE media.title = :title", nativeQuery = true)
    List<Genre> findByMediaId(String title);

}
