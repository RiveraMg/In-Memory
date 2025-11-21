package com.codeup.InMemory_HU2.service;

import com.codeup.InMemory_HU2.entities.VenueEntity;
import com.codeup.InMemory_HU2.web.dto.venue.VenueRequestDTO;
import com.codeup.InMemory_HU2.web.dto.venue.VenueResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IVenueService {

    // Crear un nuevo venue
    VenueResponseDTO createVenue(VenueRequestDTO requestDTO);  // ← Cambio aquí

    // Obtener todos los venues
    List<VenueResponseDTO> getAllVenues();  // ← Cambio aquí

    // Obtener venues con paginación
    Page<VenueResponseDTO> getAllVenuesPaginated(Pageable pageable);  // ← Cambio aquí

    // Obtener un venue por ID
    VenueResponseDTO getVenueById(Long id);  // ← Cambio aquí (ya no Optional)

    // Actualizar un venue
    VenueResponseDTO updateVenue(Long id, VenueRequestDTO requestDTO);  // ← Cambio aquí

    // Eliminar un venue
    void deleteVenue(Long id);  // ← Este está bien

    // Buscar venues por ciudad
    List<VenueResponseDTO> getVenuesByCity(String city);  // ← Cambio aquí
}