package com.daverj.media.service;

import com.daverj.media.dto.mapper.MediaMapper;
import com.daverj.media.dto.response.MediaDTO;
import com.daverj.media.dto.response.MediaMinDTO;
import com.daverj.media.repository.MediaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MediaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    public Page<MediaMinDTO> findAll(Pageable pageable) {
        return mediaRepository.findAll(pageable)
                .map(mediaMapper::toMinDTO);
    }

    public MediaDTO findByTitle(String title) {
        return mediaRepository.findByTitle(title)
                .map(mediaMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Media not found with title: " + title));
    }
}
