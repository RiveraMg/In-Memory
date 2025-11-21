package com.codeup.InMemory_HU2.web.controller;

import com.codeup.InMemory_HU2.service.IVenueService;
import com.codeup.InMemory_HU2.web.dto.venue.VenueRequestDTO;
import com.codeup.InMemory_HU2.web.dto.venue.VenueResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codeup.InMemory_HU2.web.response.AppResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/venues")
@Tag(name = "Venues", description = "API para gestión de venues")
public class VenueController {

    @Autowired
    private IVenueService venueService;

    // ===== CREATE =====
    @PostMapping
    @Operation(summary = "Crear un nuevo venue")
    public ResponseEntity<AppResponse<VenueResponseDTO>> createVenue(
            @Valid @RequestBody VenueRequestDTO requestDTO) {

        VenueResponseDTO createdVenue = venueService.createVenue(requestDTO);

        AppResponse.Meta meta = new AppResponse.Meta(
                "Venue creado exitosamente",
                UUID.randomUUID().toString(),
                "1.0.0",
                null
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AppResponse.withMeta(createdVenue, meta));
    }

    // ===== READ ALL (sin paginación) =====
    @GetMapping("/all")
    @Operation(summary = "Obtener todos los venues sin paginación")
    public ResponseEntity<AppResponse<List<VenueResponseDTO>>> getAllVenues() {

        List<VenueResponseDTO> venues = venueService.getAllVenues();

        AppResponse.Meta meta = new AppResponse.Meta(
                String.format("Se encontraron %d venues", venues.size()),
                UUID.randomUUID().toString(),
                "1.0.0",
                null
        );

        return ResponseEntity.ok(AppResponse.withMeta(venues, meta));
    }

    // ===== READ ALL (con paginación) =====
    @GetMapping
    @Operation(summary = "Listar venues con paginación")
    public ResponseEntity<AppResponse<List<VenueResponseDTO>>> getVenuesPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));

        Page<VenueResponseDTO> venuePage = venueService.getAllVenuesPaginated(pageable);

        AppResponse.Pagination pagination = new AppResponse.Pagination(
                venuePage.getNumber(),
                venuePage.getSize(),
                venuePage.getTotalElements(),
                venuePage.getTotalPages()
        );

        AppResponse.Meta meta = new AppResponse.Meta(
                String.format("Se encontraron %d venues", venuePage.getTotalElements()),
                UUID.randomUUID().toString(),
                "1.0.0",
                pagination
        );

        return ResponseEntity.ok(AppResponse.withMeta(venuePage.getContent(), meta));
    }

    // ===== READ BY ID =====
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un venue por ID")
    public ResponseEntity<AppResponse<VenueResponseDTO>> getVenueById(
            @PathVariable Long id) {

        VenueResponseDTO venue = venueService.getVenueById(id);

        return ResponseEntity.ok(AppResponse.ok(venue));
    }

    // ===== READ BY CITY (filtro) =====
    @GetMapping("/city/{city}")
    @Operation(summary = "Buscar venues por ciudad")
    public ResponseEntity<AppResponse<List<VenueResponseDTO>>> getVenuesByCity(
            @PathVariable String city) {

        List<VenueResponseDTO> venues = venueService.getVenuesByCity(city);

        AppResponse.Meta meta = new AppResponse.Meta(
                String.format("Se encontraron %d venues en %s", venues.size(), city),
                UUID.randomUUID().toString(),
                "1.0.0",
                null
        );

        return ResponseEntity.ok(AppResponse.withMeta(venues, meta));
    }

    // ===== UPDATE =====
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un venue existente")
    public ResponseEntity<AppResponse<VenueResponseDTO>> updateVenue(
            @PathVariable Long id,
            @Valid @RequestBody VenueRequestDTO requestDTO) {

        VenueResponseDTO updatedVenue = venueService.updateVenue(id, requestDTO);

        AppResponse.Meta meta = new AppResponse.Meta(
                "Venue actualizado exitosamente",
                UUID.randomUUID().toString(),
                "1.0.0",
                null
        );

        return ResponseEntity.ok(AppResponse.withMeta(updatedVenue, meta));
    }

    // ===== DELETE =====
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un venue")
    public ResponseEntity<AppResponse<Void>> deleteVenue(
            @PathVariable Long id) {

        venueService.deleteVenue(id);

        AppResponse.Meta meta = new AppResponse.Meta(
                String.format("Venue con ID %d eliminado exitosamente", id),
                UUID.randomUUID().toString(),
                "1.0.0",
                null
        );

        return ResponseEntity.ok(AppResponse.withMeta(null, meta));
    }
}