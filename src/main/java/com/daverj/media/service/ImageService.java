package com.daverj.media.service;

import com.daverj.media.dto.response.ImageDTO;
import com.daverj.media.dto.response.ImageTmdbDTO;
import com.daverj.media.exceptions.DuplicateEntityException;
import com.daverj.media.model.Image;
import com.daverj.media.model.Media;
import com.daverj.media.repository.ImageRepository;
import com.daverj.media.utils.StandardMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageService {

    private final ImageRepository imageRepository;
    private final TMDBService tmdbService;

    public Map<String, Set<ImageDTO>> findAllByMedia(Long id) {
        Set<ImageDTO> arts = imageRepository.findByMediaId(id)
                .stream().map(art -> new ImageDTO(art))
                .collect(Collectors.toSet());

        Map<String, Set<ImageDTO>> artsMap = new HashMap<>();

        artsMap.put("logo", arts.stream().filter(art -> art.getType().contains("logo")).collect(Collectors.toSet()));
        artsMap.put("poster", arts.stream().filter(art -> art.getType().contains("poster")).collect(Collectors.toSet()));
        artsMap.put("backdrop", arts.stream().filter(art -> art.getType().contains("backdrop")).collect(Collectors.toSet()));

        return artsMap;
    }

    public List<ImageDTO> findBackdropsByMedia(Long mediaId) {
        return imageRepository.findBackdropsByMedia(mediaId)
                .stream()
                .map(ImageDTO::new)
                .collect(Collectors.toList());
    }

    public List<ImageDTO> findPostersByMedia(Long mediaId) {
        return imageRepository.findPostersByMedia(mediaId)
                .stream()
                .map(ImageDTO::new)
                .collect(Collectors.toList());
    }

    public List<ImageDTO> findLogosByMedia(Long mediaId) {
        return imageRepository.findLogosByMedia(mediaId)
                .stream()
                .map(ImageDTO::new)
                .collect(Collectors.toList());
    }

    public void createPoster(Long idMedia, String titleMedia, Long idMediaTmdb, String filePath, String type) {
        Random rand = new Random();
        Optional<ImageDTO> existsArt = imageRepository.findAll()
                .stream()
                .filter(art -> art.getFilePath().contains(filePath))
                .map(ImageDTO::new)
                .findFirst();

        if (existsArt.isPresent()) {
            throw new DuplicateEntityException("This art is already added!");
        }

        if (type.equals("poster")) {

            Optional<ImageTmdbDTO> posterFromTmdb = tmdbService.getPosterFromResponseByTmdbApi(filePath, idMediaTmdb);

            if (posterFromTmdb.isPresent()) {

                Media media = new Media();
                media.setId(idMedia);
                media.setTitle(titleMedia);
                Image poster = new Image(
                        titleMedia.toLowerCase().replace(" ", "-")+"-"+type+"-"+rand.nextInt(1000000),
                        "https://image.tmdb.org/t/p/w300" + posterFromTmdb.get().getFile_path(),
                        type,
                        posterFromTmdb.get().getAspect_ratio(),
                        posterFromTmdb.get().getHeight(),
                        posterFromTmdb.get().getWidth(),
                        media
                );

                new ImageDTO(imageRepository.save(poster));
            }

        }

        if (type.equals("backdrop")) {

            Optional<ImageTmdbDTO> backdropFromTmdb = tmdbService.getBackdropFromResponseByTmdbApi(filePath, idMediaTmdb);

            if (backdropFromTmdb.isPresent()) {

                Media media = new Media();
                media.setId(idMedia);
                media.setTitle(titleMedia);
                Image poster = new Image(
                        titleMedia.toLowerCase().replace(" ", "-")+"-"+type+"-"+rand.nextInt(1000000),
                        "https://image.tmdb.org/t/p/original" + backdropFromTmdb.get().getFile_path(),
                        type,
                        backdropFromTmdb.get().getAspect_ratio(),
                        backdropFromTmdb.get().getHeight(),
                        backdropFromTmdb.get().getWidth(),
                        media
                );

                new ImageDTO(imageRepository.save(poster));
            }

        }

        if (type.equals("logo")) {

            Optional<ImageTmdbDTO> logoFromTmdb = tmdbService.getLogoFromResponseByTmdbApi(filePath, idMediaTmdb);

            if (logoFromTmdb.isPresent()) {

                Media media = new Media();
                media.setId(idMedia);
                media.setTitle(titleMedia);
                Image poster = new Image(
                        titleMedia.toLowerCase().replace(" ", "-")+"-"+type+"-"+rand.nextInt(1000000),
                        "https://image.tmdb.org/t/p/original" + logoFromTmdb.get().getFile_path(),
                        type,
                        logoFromTmdb.get().getAspect_ratio(),
                        logoFromTmdb.get().getHeight(),
                        logoFromTmdb.get().getWidth(),
                        media
                );

                new ImageDTO(imageRepository.save(poster));
            }

        }

    }

    @Transactional
    public StandardMessage selectImage(Long mediaId, Long imageId, String type) {
        imageRepository.selectImage(mediaId, imageId, type);
        return new StandardMessage("success", "Image selected");
    }

}
