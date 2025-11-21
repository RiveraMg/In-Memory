package com.codeup.InMemory_HU2.service.Impl;

import com.codeup.InMemory_HU2.domain.EventStatus;
import com.codeup.InMemory_HU2.entities.EventEntity;
import com.codeup.InMemory_HU2.entities.VenueEntity;
import com.codeup.InMemory_HU2.repository.EventRepository;
import com.codeup.InMemory_HU2.repository.VenueRepository;
import com.codeup.InMemory_HU2.service.IEventService;
import com.codeup.InMemory_HU2.web.dto.event.EventRequestDTO;
import com.codeup.InMemory_HU2.web.dto.event.EventResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Override
    public EventResponseDTO createEvent(EventRequestDTO requestDTO) {
        // VALIDACIÓN 1: Verificar que no exista un evento con el mismo nombre
        if (eventRepository.existsByName(requestDTO.getName())) {
            throw new IllegalArgumentException(
                    String.format("Error de validación: Ya existe un evento con el nombre '%s'. " +
                            "Por favor, elija un nombre diferente.", requestDTO.getName())
            );
        }

        // VALIDACIÓN 2: Verificar que el venue exista
        VenueEntity venue = venueRepository.findById(requestDTO.getIdVenue())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Error de validación: El venue con ID %d no existe. " +
                                        "Verifique el ID del venue e intente nuevamente.",
                                requestDTO.getIdVenue())
                ));

        // VALIDACIÓN 3: Verificar que el status sea válido
        EventStatus status;
        try {
            status = EventStatus.valueOf(requestDTO.getStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Error de validación: El estado '%s' no es válido. " +
                                    "Los valores permitidos son: ACTIVE, CANCELLED, COMPLETED, POSTPONED.",
                            requestDTO.getStatus())
            );
        }

        // Convertir DTO a Entity
        EventEntity eventEntity = convertToEntity(requestDTO, venue, status);

        // Guardar en la base de datos
        EventEntity savedEvent = eventRepository.save(eventEntity);

        // Convertir Entity a DTO de respuesta
        return new EventResponseDTO(savedEvent);
    }

    @Override
    public List<EventResponseDTO> getAllEvents() {
        return eventRepository.findAll()
                .stream()
                .map(EventResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EventResponseDTO> getAllEventsPaginated(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(EventResponseDTO::new);
    }

    @Override
    public Page<EventResponseDTO> getEventsWithFilters(String city, String status,
                                                       LocalDateTime fechaInicio,
                                                       Pageable pageable) {
        // Convertir status de String a Enum (si viene)
        EventStatus eventStatus = null;
        if (status != null && !status.isBlank()) {
            try {
                eventStatus = EventStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(
                        String.format("Error de validación: El estado '%s' no es válido. " +
                                        "Los valores permitidos son: ACTIVE, CANCELLED, COMPLETED, POSTPONED.",
                                status)
                );
            }
        }

        // Llamar al repository con los filtros
        return eventRepository.findWithFilters(city, eventStatus, fechaInicio, pageable)
                .map(EventResponseDTO::new);
    }

    @Override
    public EventResponseDTO getEventById(Long id) {
        EventEntity event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Error: El evento con ID %d no fue encontrado.", id)
                ));

        return new EventResponseDTO(event);
    }

    @Override
    public EventResponseDTO updateEvent(Long id, EventRequestDTO requestDTO) {
        // VALIDACIÓN 1: Verificar si el evento existe
        EventEntity existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Error: El evento con ID %d no fue encontrado.", id)
                ));

        // VALIDACIÓN 2: Validar nombre único (solo si cambió el nombre)
        if (!existingEvent.getName().equals(requestDTO.getName()) &&
                eventRepository.existsByName(requestDTO.getName())) {
            throw new IllegalArgumentException(
                    String.format("Error de validación: Ya existe un evento con el nombre '%s'. " +
                            "Por favor, elija un nombre diferente.", requestDTO.getName())
            );
        }

        // VALIDACIÓN 3: Verificar que el venue exista
        VenueEntity venue = venueRepository.findById(requestDTO.getIdVenue())
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Error de validación: El venue con ID %d no existe.",
                                requestDTO.getIdVenue())
                ));

        // VALIDACIÓN 4: Verificar que el status sea válido
        EventStatus status;
        try {
            status = EventStatus.valueOf(requestDTO.getStatus().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    String.format("Error de validación: El estado '%s' no es válido. " +
                                    "Los valores permitidos son: ACTIVE, CANCELLED, COMPLETED, POSTPONED.",
                            requestDTO.getStatus())
            );
        }

        // Actualizar campos
        existingEvent.setName(requestDTO.getName());
        existingEvent.setHoster(requestDTO.getHoster());
        existingEvent.setDescription(requestDTO.getDescription());
        existingEvent.setDate(requestDTO.getDate());
        existingEvent.setStatus(status);
        existingEvent.setVenue(venue);

        // Guardar cambios
        EventEntity updatedEvent = eventRepository.save(existingEvent);

        return new EventResponseDTO(updatedEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    String.format("Error: El evento con ID %d no fue encontrado.", id)
            );
        }
        eventRepository.deleteById(id);
    }

    // ===== MÉTODOS PRIVADOS DE CONVERSIÓN =====

    private EventEntity convertToEntity(EventRequestDTO dto, VenueEntity venue, EventStatus status) {
        EventEntity entity = new EventEntity();
        entity.setName(dto.getName());
        entity.setHoster(dto.getHoster());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setStatus(status);
        entity.setVenue(venue);
        return entity;
    }
}
