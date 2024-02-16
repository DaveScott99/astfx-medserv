package com.daverj.media.service;

import com.daverj.media.dto.response.ArtDTO;
import com.daverj.media.model.Art;
import com.daverj.media.repository.ArtRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

        // Verify if exists selected arts on media
        String typeArt = art.getType();

        Optional<Art> selectedMedia = artRepository.findByMediaId(art.getMedia().getId())
                .stream()
                .filter(item -> item.getType().contains(typeArt))
                .filter(Art::isSelected)
                .findFirst();

        log.info("" + selectedMedia.get());

        if (selectedMedia.get().isSelected() && art.isSelected()) {
            log.error("Error creating art...");
            throw new RuntimeException("Already exists selected " + typeArt);
        }
        else {
            return new ArtDTO(artRepository.save(art));
        }

    }

}
