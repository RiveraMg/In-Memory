package com.codeup.InMemory_HU2.web.controller;

import com.codeup.InMemory_HU2.service.IEventService;
import com.codeup.InMemory_HU2.web.dto.event.EventRequestDTO;
import com.codeup.InMemory_HU2.web.dto.event.EventResponseDTO;
import com.codeup.InMemory_HU2.web.response.AppResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@Tag(name = "Events", description = "API para gestión de eventos")
public class EventController {

    @Autowired
    private IEventService eventService;

    // ===== CREATE =====
    @PostMapping
    @Operation(summary = "Crear un nuevo evento", description = "Crea un evento con validaciones completas")
    public ResponseEntity<AppResponse<EventResponseDTO>> createEvent(
            @Valid @RequestBody EventRequestDTO requestDTO) {

        EventResponseDTO createdEvent = eventService.createEvent(requestDTO);

        AppResponse.Meta meta = new AppResponse.Meta(
                "Evento creado exitosamente",
                UUID.randomUUID().toString(),
                "1.0.0",
                null
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AppResponse.withMeta(createdEvent, meta));
    }

    // ===== READ ALL (sin paginación) =====
    @GetMapping("/all")
    @Operation(summary = "Obtener todos los eventos sin paginación")
    public ResponseEntity<AppResponse<List<EventResponseDTO>>> getAllEvents() {

        List<EventResponseDTO> events = eventService.getAllEvents();

        AppResponse.Meta meta = new AppResponse.Meta(
                String.format("Se encontraron %d eventos", events.size()),
                UUID.randomUUID().toString(),
                "1.0.0",
                null
        );

        return ResponseEntity.ok(AppResponse.withMeta(events, meta));
    }

    // ===== READ ALL (con paginación y filtros) - TASK 3 =====
    @GetMapping
    @Operation(
            summary = "Listar eventos con paginación y filtros",
            description = "Permite filtrar por ciudad, status y fecha de inicio. Soporta ordenamiento y paginación."
    )
    public ResponseEntity<AppResponse<List<EventResponseDTO>>> getEventsWithFilters(
            @RequestParam(required = false) String ciudad,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {

        // Crear el objeto Sort
        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        // Crear el Pageable
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sort));

        // Llamar al servicio con filtros
        Page<EventResponseDTO> eventPage = eventService.getEventsWithFilters(
                ciudad, categoria, fechaInicio, pageable
        );

        // Crear objeto de paginación
        AppResponse.Pagination pagination = new AppResponse.Pagination(
                eventPage.getNumber(),
                eventPage.getSize(),
                eventPage.getTotalElements(),
                eventPage.getTotalPages()
        );

        // Crear metadata
        AppResponse.Meta meta = new AppResponse.Meta(
                String.format("Se encontraron %d eventos", eventPage.getTotalElements()),
                UUID.randomUUID().toString(),
                "1.0.0",
                pagination
        );

        return ResponseEntity.ok(AppResponse.withMeta(eventPage.getContent(), meta));
    }

    // ===== READ BY ID =====
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un evento por ID")
    public ResponseEntity<AppResponse<EventResponseDTO>> getEventById(
            @PathVariable Long id) {

        EventResponseDTO event = eventService.getEventById(id);

        return ResponseEntity.ok(AppResponse.ok(event));
    }

    // ===== UPDATE =====
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un evento existente")
    public ResponseEntity<AppResponse<EventResponseDTO>> updateEvent(
            @PathVariable Long id,
            @Valid @RequestBody EventRequestDTO requestDTO) {

        EventResponseDTO updatedEvent = eventService.updateEvent(id, requestDTO);

        AppResponse.Meta meta = new AppResponse.Meta(
                "Evento actualizado exitosamente",
                UUID.randomUUID().toString(),
                "1.0.0",
                null
        );

        return ResponseEntity.ok(AppResponse.withMeta(updatedEvent, meta));
    }

    // ===== DELETE =====
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un evento")
    public ResponseEntity<AppResponse<Void>> deleteEvent(
            @PathVariable Long id) {

        eventService.deleteEvent(id);

        AppResponse.Meta meta = new AppResponse.Meta(
                String.format("Evento con ID %d eliminado exitosamente", id),
                UUID.randomUUID().toString(),
                "1.0.0",
                null
        );

        return ResponseEntity.ok(AppResponse.withMeta(null, meta));
    }
}
