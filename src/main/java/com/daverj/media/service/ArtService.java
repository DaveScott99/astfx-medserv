package com.daverj.media.service;

import com.daverj.media.dto.response.ArtDTO;
import com.daverj.media.dto.response.ImageTmdbDTO;
import com.daverj.media.exceptions.DuplicateEntityException;
import com.daverj.media.model.Art;
import com.daverj.media.model.Media;
import com.daverj.media.repository.ArtRepository;
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
public class ArtService {

    private final ArtRepository artRepository;
    private final TMDBService tmdbService;

    public Map<String, Set<ArtDTO>> findAllByMedia(Long id) {
        Set<ArtDTO> arts = artRepository.findByMediaId(id)
                .stream().map(art -> new ArtDTO(art))
                .collect(Collectors.toSet());

        Map<String, Set<ArtDTO>> artsMap = new HashMap<>();

        artsMap.put("logo", arts.stream().filter(art -> art.getType().contains("logo")).collect(Collectors.toSet()));
        artsMap.put("poster", arts.stream().filter(art -> art.getType().contains("poster")).collect(Collectors.toSet()));
        artsMap.put("backdrop", arts.stream().filter(art -> art.getType().contains("backdrop")).collect(Collectors.toSet()));

        return artsMap;
    }

    public List<ArtDTO> findBackdropsByMedia(Long mediaId) {
        return artRepository.findBackdropsByMedia(mediaId)
                .stream()
                .map(ArtDTO::new)
                .collect(Collectors.toList());
    }

    public List<ArtDTO> findPostersByMedia(Long mediaId) {
        return artRepository.findPostersByMedia(mediaId)
                .stream()
                .map(ArtDTO::new)
                .collect(Collectors.toList());
    }

    public List<ArtDTO> findLogosByMedia(Long mediaId) {
        return artRepository.findLogosByMedia(mediaId)
                .stream()
                .map(ArtDTO::new)
                .collect(Collectors.toList());
    }

    public void createPoster(Long idMedia, String titleMedia, Long idMediaTmdb, String filePath, String type) {
        Random rand = new Random();
        Optional<ArtDTO> existsArt = artRepository.findAll()
                .stream()
                .filter(art -> art.getFilePath().contains(filePath))
                .map(ArtDTO::new)
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
                Art poster = new Art(
                        titleMedia.toLowerCase().replace(" ", "-")+"-"+type+"-"+rand.nextInt(1000000),
                        "https://image.tmdb.org/t/p/w300" + posterFromTmdb.get().getFile_path(),
                        type,
                        posterFromTmdb.get().getAspect_ratio(),
                        posterFromTmdb.get().getHeight(),
                        posterFromTmdb.get().getWidth(),
                        media
                );

                new ArtDTO(artRepository.save(poster));
            }

        }

        if (type.equals("backdrop")) {

            Optional<ImageTmdbDTO> backdropFromTmdb = tmdbService.getBackdropFromResponseByTmdbApi(filePath, idMediaTmdb);

            if (backdropFromTmdb.isPresent()) {

                Media media = new Media();
                media.setId(idMedia);
                media.setTitle(titleMedia);
                Art poster = new Art(
                        titleMedia.toLowerCase().replace(" ", "-")+"-"+type+"-"+rand.nextInt(1000000),
                        "https://image.tmdb.org/t/p/original" + backdropFromTmdb.get().getFile_path(),
                        type,
                        backdropFromTmdb.get().getAspect_ratio(),
                        backdropFromTmdb.get().getHeight(),
                        backdropFromTmdb.get().getWidth(),
                        media
                );

                new ArtDTO(artRepository.save(poster));
            }

        }

        if (type.equals("logo")) {

            Optional<ImageTmdbDTO> logoFromTmdb = tmdbService.getLogoFromResponseByTmdbApi(filePath, idMediaTmdb);

            if (logoFromTmdb.isPresent()) {

                Media media = new Media();
                media.setId(idMedia);
                media.setTitle(titleMedia);
                Art poster = new Art(
                        titleMedia.toLowerCase().replace(" ", "-")+"-"+type+"-"+rand.nextInt(1000000),
                        "https://image.tmdb.org/t/p/original" + logoFromTmdb.get().getFile_path(),
                        type,
                        logoFromTmdb.get().getAspect_ratio(),
                        logoFromTmdb.get().getHeight(),
                        logoFromTmdb.get().getWidth(),
                        media
                );

                new ArtDTO(artRepository.save(poster));
            }

        }

    }

    @Transactional
    public StandardMessage selectLogo(Long mediaId, Long logoId) {
        artRepository.selectLogo(mediaId, logoId);

        return new StandardMessage("success", "Logo selected");
    }

    @Transactional
    public StandardMessage selectPoster(Long mediaId, Long posterId) {
        artRepository.selectPoster(mediaId, posterId);

        return new StandardMessage("success", "Poster selected");
    }

    @Transactional
    public StandardMessage selectBackdrop(Long mediaId, Long backdropId) {
        artRepository.selectBackdrop(mediaId, backdropId);

        return new StandardMessage("success", "Backdrop selected");
    }


}
