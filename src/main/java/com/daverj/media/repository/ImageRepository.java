package com.daverj.media.repository;

import com.daverj.media.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Set<Image> findByMediaId(Long id);

    @Query(value = "CALL findBackdropsByMedia(:mediaId)", nativeQuery = true)
    List<Image> findBackdropsByMedia(Long mediaId);

    @Query(value = "CALL findPostersByMedia(:mediaId)", nativeQuery = true)
    List<Image> findPostersByMedia(Long mediaId);

    @Query(value = "CALL findLogosByMedia(:mediaId)", nativeQuery = true)
    List<Image> findLogosByMedia(Long mediaId);

    @Query(value = "CALL select_image(:mediaId, :imageId, :type) ", nativeQuery = true)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    int selectImage(Long mediaId, Long imageId, String type);

}
