package com.example.back.controllers;

import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.Catalog.CatalogDTO;
import com.example.back.service.CatalogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
@CrossOrigin(origins = "http://localhost:3000")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public APIResponse<List<CatalogDTO>> getAll() {
        return new APIResponse<>(1000, "OK", catalogService.getAllCatalogs());
    }

    @PostMapping
    public APIResponse<CatalogDTO> create(@RequestBody CatalogDTO dto) {
        CatalogDTO created = catalogService.createCatalog(dto);
        return new APIResponse<>(1000, "Created", created);
    }

    @PutMapping("/{catalogId}")
    public APIResponse<CatalogDTO> update(@PathVariable Integer catalogId, @RequestBody CatalogDTO dto) {
        CatalogDTO updated = catalogService.updateCatalog(catalogId, dto);
        return new APIResponse<>(1000, "Updated", updated);
    }

    @DeleteMapping("/{catalogId}")
    public APIResponse<Void> delete(@PathVariable Integer catalogId) {
        catalogService.deleteCatalog(catalogId);
        return new APIResponse<>(1000, "Deleted", null);
    }
    @GetMapping("/paged")
    public APIResponse<Page<CatalogDTO>> getPagedCatalogs(
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CatalogDTO> pagedResult = catalogService.getCatalogsPage(search, pageable);
        return new APIResponse<>(1000, "OK", pagedResult);
    }

}
