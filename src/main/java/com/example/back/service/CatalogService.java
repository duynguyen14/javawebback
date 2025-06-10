package com.example.back.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.back.dto.response.Catalog.CatalogDTO;
import com.example.back.entity.Catalog;
import com.example.back.mapper.CatalogMapper;
import com.example.back.repository.CatalogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogRepository catalogRepository;
    private final CatalogMapper catalogMapper;

    public Page<CatalogDTO> getCatalogsPage(String search, Pageable pageable) {
        Page<Catalog> page;
        if (search == null || search.isEmpty()) {
            page = catalogRepository.findAll(pageable);
        } else {
            page = catalogRepository.findByNameContainingIgnoreCase(search, pageable);
        }
        return page.map(catalogMapper::toDto);
    }
    public List<CatalogDTO> getAllCatalogs() {
        return catalogRepository.findAll().stream()
                .map(catalogMapper::toDto)
                .toList();
    }

    public CatalogDTO createCatalog(CatalogDTO dto) {
        Catalog catalog = Catalog.builder()
                .name(dto.getName())
                .build();
        Catalog saved = catalogRepository.save(catalog);
        return catalogMapper.toDto(saved);
    }

    public CatalogDTO updateCatalog(Integer catalogId, CatalogDTO dto) {
        Catalog catalog = catalogRepository.findById(catalogId)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy danh mục"));
        catalog.setName(dto.getName());
        Catalog updated = catalogRepository.save(catalog);
        return catalogMapper.toDto(updated);
    }

    public void deleteCatalog(Integer catalogId) {
        if (!catalogRepository.existsById(catalogId)) {
            throw new NoSuchElementException("Không tìm thấy danh mục với id = " + catalogId);
        }
        catalogRepository.deleteById(catalogId);
    }
}
