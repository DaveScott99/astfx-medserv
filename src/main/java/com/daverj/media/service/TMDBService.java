package com.daverj.media.service;

import com.daverj.media.dto.response.ImageTmdbDTO;
import com.daverj.media.dto.response.ListsImageTmdbDTO;
import com.daverj.media.model.Art;
import com.daverj.media.model.Media;
import com.daverj.media.utils.UtilMethods;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TMDBService {

    private static final String TMDB_TOKEN = "39fcbb59ca74494324fa1fdc9407bd52";
    private final UtilMethods utilMethods;

    public Object findMovie(@RequestParam Long idMovieTmdb) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate
                .getForEntity("https://api.themoviedb.org/3/movie/"+idMovieTmdb+"?language=en-US&api_key="+TMDB_TOKEN, Object.class);
        return response.getBody();
    }

    public Page<ImageTmdbDTO> searchLogosByMovie(Pageable pageable, Long idMovieTmdb) {
        RestTemplate restTemplate = new RestTemplate();
        ListsImageTmdbDTO response = restTemplate
                .getForObject("https://api.themoviedb.org/3/movie/"+idMovieTmdb+"/images?language=en&api_key="+TMDB_TOKEN, ListsImageTmdbDTO.class);

        List<ImageTmdbDTO> logos = response.getLogos();

        return utilMethods.convertListToPagination(pageable, logos);
    }

    public Page<ImageTmdbDTO> searchPostersByMovie(Pageable pageable, Long idMovieTmdb) {
        RestTemplate restTemplate = new RestTemplate();
        ListsImageTmdbDTO response = restTemplate
                .getForObject("https://api.themoviedb.org/3/movie/"+idMovieTmdb+"/images?language=en&api_key="+TMDB_TOKEN, ListsImageTmdbDTO.class);

        List<ImageTmdbDTO> posters = response.getPosters();

        return utilMethods.convertListToPagination(pageable, posters);
    }

    public Page<ImageTmdbDTO> searchBackdropsByMovie(Pageable pageable, Long idMovieTmdb) {
        RestTemplate restTemplate = new RestTemplate();
        ListsImageTmdbDTO response = restTemplate
                .getForObject("https://api.themoviedb.org/3/movie/"+idMovieTmdb+"/images?language=null&api_key="+TMDB_TOKEN, ListsImageTmdbDTO.class);

        List<ImageTmdbDTO> backdrops = response.getBackdrops();

        return utilMethods.convertListToPagination(pageable, backdrops);
    }

    public Optional<ImageTmdbDTO> getBackdropFromResponseByTmdbApi(String filePath, Long idMediaTmdb) {

        RestTemplate restTemplate = new RestTemplate();
        ListsImageTmdbDTO response = restTemplate
                .getForObject("https://api.themoviedb.org/3/movie/"+idMediaTmdb+"/images?language=null&api_key="+TMDB_TOKEN, ListsImageTmdbDTO.class);

        List<ImageTmdbDTO> backdrops = response.getBackdrops();

        return backdrops
                .stream()
                .filter(backdrop -> backdrop.getFile_path().equals(filePath))
                .map(ImageTmdbDTO::new)
                .findFirst();
    }

    public Optional<ImageTmdbDTO> getPosterFromResponseByTmdbApi(String filePath, Long idMediaTmdb) {

        RestTemplate restTemplate = new RestTemplate();
        ListsImageTmdbDTO response = restTemplate
                .getForObject("https://api.themoviedb.org/3/movie/"+idMediaTmdb+"/images?language=en&api_key="+TMDB_TOKEN, ListsImageTmdbDTO.class);

        List<ImageTmdbDTO> posters = response.getPosters();

        return posters
                .stream()
                .filter(poster -> poster.getFile_path().equals(filePath))
                .map(ImageTmdbDTO::new)
                .findFirst();
    }

    public Optional<ImageTmdbDTO> getLogoFromResponseByTmdbApi(String filePath, Long idMediaTmdb) {

        RestTemplate restTemplate = new RestTemplate();
        ListsImageTmdbDTO response = restTemplate
                .getForObject("https://api.themoviedb.org/3/movie/"+idMediaTmdb+"/images?language=en&api_key="+TMDB_TOKEN, ListsImageTmdbDTO.class);

        List<ImageTmdbDTO> logos = response.getLogos();

        return logos
                .stream()
                .filter(logo -> logo.getFile_path().equals(filePath))
                .map(ImageTmdbDTO::new)
                .findFirst();
    }

}
