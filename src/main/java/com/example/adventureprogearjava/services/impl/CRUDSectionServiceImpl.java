package com.example.adventureprogearjava.services.impl;

import com.example.adventureprogearjava.dto.SectionDTO;
import com.example.adventureprogearjava.entity.ProductContent;
import com.example.adventureprogearjava.entity.Section;
import com.example.adventureprogearjava.exceptions.NoContentException;
import com.example.adventureprogearjava.exceptions.ResourceNotFoundException;
import com.example.adventureprogearjava.mapper.SectionMapper;
import com.example.adventureprogearjava.repositories.SectionRepository;
import com.example.adventureprogearjava.services.CRUDService;
import com.example.adventureprogearjava.services.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CRUDSectionServiceImpl implements CRUDService<SectionDTO> {
    SectionRepository sectionRepository;

    SectionMapper sectionMapper;

    CategoryService categoryService;

    @Override
    public List<SectionDTO> getAll() {
        log.info("Getting all sections with categories");
        return sectionRepository
                .findAll()
                .stream()
                .map(sectionMapper::toDto)
                .map(this::joinAllCategories)
                .toList();
    }

    @Override
    public SectionDTO getById(Long id) {
        log.info("Getting section by id: {}", id);
        Optional<Section> section = sectionRepository.findById(id);
        if (section.isEmpty()) {
            log.warn("Section not found!");
            throw new ResourceNotFoundException("Resource is not available!");
        }
        return section.map(sectionMapper::toDto).get();
    }

    @Override
    @Transactional
    public SectionDTO create(SectionDTO sectionDTO) {
        log.info("Creating new section.");
        sectionRepository.save(sectionMapper.toEntity(sectionDTO));
        return sectionDTO;
    }

    @Override
    @Transactional
    public void update(SectionDTO sectionDTO, Long id) {
        log.info("Updating section with id: {}", id);
        if (!sectionRepository.existsById(id)) {
            log.warn("Section not found!");
            throw new ResourceNotFoundException("Resource is not available!");
        } else {
            sectionRepository.update(id, sectionDTO.getSectionCaptionEn(),
                    sectionDTO.getSectionCaptionUa(), sectionDTO.getSectionIcon());
        }
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting section with id: {}", id);
        if (!sectionRepository.existsById(id)) {
            log.warn("No content present!");
            throw new NoContentException("No content present!");
        }
        sectionRepository.deleteById(id);
    }

    private SectionDTO joinAllCategories(SectionDTO sectionDTO) {
        sectionDTO.setCategories(categoryService.getAllCategoriesBySection(sectionDTO.getId()));
        return sectionDTO;
    }
}
