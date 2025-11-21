package com.codeup.InMemory_HU2.service;

import com.codeup.InMemory_HU2.entities.EventEntity;
import com.codeup.InMemory_HU2.web.dto.event.EventRequestDTO;
import com.codeup.InMemory_HU2.web.dto.event.EventResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IEventService {

    // Crear un nuevo evento
    EventResponseDTO createEvent(EventRequestDTO requestDTO);

    // Obtener todos los eventos
    List<EventResponseDTO> getAllEvents();

    // Obtener eventos con paginación
    Page<EventResponseDTO> getAllEventsPaginated(Pageable pageable);

    // NUEVO: Obtener eventos con filtros y paginación
    Page<EventResponseDTO> getEventsWithFilters(String city, String status,
                                                LocalDateTime fechaInicio, Pageable pageable);

    // Obtener un evento por ID
    EventResponseDTO getEventById(Long id);

    // Actualizar un evento
    EventResponseDTO updateEvent(Long id, EventRequestDTO requestDTO);

    // Eliminar un evento
    void deleteEvent(Long id);
}
