package com.codeup.In_Memory.web.controller;

import com.codeup.In_Memory.service.CatalogService;
import com.codeup.In_Memory.web.dto.venue.VenueRequestDTO;
import com.codeup.In_Memory.web.dto.venue.VenueResponseDTO;
import com.codeup.In_Memory.web.reponse.AppResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/venues")
public class VenueController {

    private final CatalogService catalogService;

    public VenueController(CatalogService catalogService) {
        this.catalogService = catalogService;
        catalogService.seedData();
    }

    // =====================
    // CREATE
    // =====================
    @PostMapping
    public ResponseEntity<AppResponse<VenueResponseDTO>> createVenue(@RequestBody VenueRequestDTO request) {
        VenueResponseDTO venue = catalogService.createVenue(
                request.getName(),
                request.getCountry(),
                request.getCity(),
                request.getLocation(),
                request.getStatus()
        );

        AppResponse<VenueResponseDTO> response = AppResponse.withMeta(
                venue,
                new AppResponse.Meta("Lugar añadido exitosamente", "trace-010", "1.0.0", null)
        );

        return ResponseEntity.ok(response);
    }

    // =====================
    // READ ALL
    // =====================
    @GetMapping
    public ResponseEntity<AppResponse<List<VenueResponseDTO>>> getAllVenues() {
        List<VenueResponseDTO> venues = catalogService.getAllVenues();

        AppResponse<List<VenueResponseDTO>> response = AppResponse.withMeta(
                venues,
                new AppResponse.Meta("Lista de lugares obtenida exitosamente", "trace-011", "1.0.0", null)
        );

        return ResponseEntity.ok(response);
    }

    // =====================
    // READ BY ID
    // =====================
    @GetMapping("/{id}")
    public ResponseEntity<AppResponse<VenueResponseDTO>> getVenueById(@PathVariable Long id) {
        Optional<VenueResponseDTO> venueOpt = catalogService.getAllVenues().stream()
                .filter(v -> v.getId().equals(id))
                .findFirst();

        if (venueOpt.isEmpty()) {
            AppResponse<VenueResponseDTO> notFound = AppResponse.withMeta(
                    null,
                    new AppResponse.Meta("Lugar no encontrado", "trace-012", "1.0.0", null)
            );
            return ResponseEntity.status(404).body(notFound);
        }

        AppResponse<VenueResponseDTO> found = AppResponse.withMeta(
                venueOpt.get(),
                new AppResponse.Meta("Lugar encontrado exitosamente", "trace-013", "1.0.0", null)
        );
        return ResponseEntity.ok(found);
    }

    // =====================
    // DELETE
    // =====================
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> deleteVenue(@PathVariable Long id) {
        boolean deleted = catalogService.deleteVenue(id);

        String message = deleted ? "Lugar eliminado exitosamente" : "Lugar no encontrado";
        AppResponse<String> response = AppResponse.withMeta(
                message,
                new AppResponse.Meta(message, "trace-014", "1.0.0", null)
        );

        return ResponseEntity.ok(response);
    }
}

