package com.daverj.media.service;

import com.daverj.media.dto.response.ArtDTO;
import com.daverj.media.model.Art;
import com.daverj.media.repository.ArtRepository;
import com.daverj.media.utils.StandardMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ArtService {

    private final ArtRepository artRepository;

    public Map<String, Set<ArtDTO>> findAllByMedia(Long id) {
        Set<ArtDTO> arts = artRepository.findByMediaId(id)
                .stream().map(art -> new ArtDTO(art))
                .collect(Collectors.toSet());

        arts.forEach(System.out::println);

        Map<String, Set<ArtDTO>> artsMap = new HashMap<>();

        artsMap.put("logo", arts.stream().filter(art -> art.getType().contains("logo")).collect(Collectors.toSet()));
        artsMap.put("poster", arts.stream().filter(art -> art.getType().contains("poster")).collect(Collectors.toSet()));
        artsMap.put("backdrop", arts.stream().filter(art -> art.getType().contains("backdrop")).collect(Collectors.toSet()));

        return artsMap;
    }

    public ArtDTO create(Art art) {
        return new ArtDTO(artRepository.save(art));
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
