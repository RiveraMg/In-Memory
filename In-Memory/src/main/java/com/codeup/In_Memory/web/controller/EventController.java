package com.codeup.In_Memory.web.controller;

import com.codeup.In_Memory.web.dto.event.EventRequestDTO;
import com.codeup.In_Memory.web.dto.event.EventResponseDTO;
import com.codeup.In_Memory.service.CatalogService;
import com.codeup.In_Memory.web.reponse.AppResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final CatalogService catalogService;
    private static boolean dataLoaded = false; // Evitar duplicar los datos

    public EventController(CatalogService catalogService) {
        this.catalogService = catalogService;
        if (!dataLoaded) {
            catalogService.seedData();
            dataLoaded = true;
        }
    }

    // =====================
    // CREATE
    // =====================
    @PostMapping
    public ResponseEntity<AppResponse<EventResponseDTO>> createEvent(@RequestBody EventRequestDTO request) {
        EventResponseDTO event = catalogService.createEvent(
                request.getName(),
                request.getHoster(),
                request.getDescription(),
                request.getDate(),
                request.getStatus(),
                request.getIdVenue()
        );

        AppResponse<EventResponseDTO> response = AppResponse.withMeta(
                event,
                new AppResponse.Meta("Evento añadido exitosamente", "trace-001", "1.0.0", null)
        );

        return ResponseEntity.ok(response);
    }

    // =====================
    // READ ALL
    // =====================
    @GetMapping
    public ResponseEntity<AppResponse<List<EventResponseDTO>>> getAllEvents() {
        List<EventResponseDTO> events = catalogService.getAllEvents();
        AppResponse<List<EventResponseDTO>> response = AppResponse.withMeta(
                events,
                new AppResponse.Meta("Lista de eventos obtenida exitosamente", "trace-002", "1.0.0", null)
        );
        return ResponseEntity.ok(response);
    }

    // =====================
    // READ BY ID
    // =====================
    @GetMapping("/{id}")
    public ResponseEntity<AppResponse<EventResponseDTO>> getEventById(@PathVariable Long id) {
        Optional<EventResponseDTO> event = catalogService.getEventById(id);

        if (event.isEmpty()) {
            AppResponse<EventResponseDTO> notFound = AppResponse.withMeta(
                    null,
                    new AppResponse.Meta("Evento no encontrado", "trace-003", "1.0.0", null)
            );
            return ResponseEntity.status(404).body(notFound);
        }

        AppResponse<EventResponseDTO> found = AppResponse.withMeta(
                event.get(),
                new AppResponse.Meta("Evento encontrado exitosamente", "trace-004", "1.0.0", null)
        );
        return ResponseEntity.ok(found);
    }

    // =====================
    // DELETE
    // =====================
    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponse<String>> deleteEvent(@PathVariable Long id) {
        boolean deleted = catalogService.deleteEvent(id);

        String message = deleted ? "Evento eliminado exitosamente" : "Evento no encontrado";
        AppResponse<String> response = AppResponse.withMeta(
                message,
                new AppResponse.Meta(message, "trace-005", "1.0.0", null)
        );

        return ResponseEntity.ok(response);
    }
}



