package com.codeup.InMemory_HU2.service.Impl;

import com.codeup.InMemory_HU2.entities.VenueEntity;
import com.codeup.InMemory_HU2.repository.VenueRepository;
import com.codeup.InMemory_HU2.service.IVenueService;
import com.codeup.InMemory_HU2.web.dto.venue.VenueRequestDTO;
import com.codeup.InMemory_HU2.web.dto.venue.VenueResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenueServiceImpl implements IVenueService {

    @Autowired
    private VenueRepository venueRepository;

    @Override
    public VenueResponseDTO createVenue(VenueRequestDTO requestDTO) {
        // Convertir DTO a Entity
        VenueEntity venueEntity = convertToEntity(requestDTO);

        // Guardar en la base de datos
        VenueEntity savedVenue = venueRepository.save(venueEntity);

        // Convertir Entity a DTO de respuesta
        return convertToDTO(savedVenue);
    }

    @Override
    public List<VenueResponseDTO> getAllVenues() {
        return venueRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<VenueResponseDTO> getAllVenuesPaginated(Pageable pageable) {
        return venueRepository.findAll(pageable)
                .map(this::convertToDTO);
    }

    @Override
    public VenueResponseDTO getVenueById(Long id) {
        VenueEntity venue = venueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Error: El venue con ID %d no fue encontrado.", id)
                ));

        return convertToDTO(venue);
    }

    @Override
    public VenueResponseDTO updateVenue(Long id, VenueRequestDTO requestDTO) {
        VenueEntity existingVenue = venueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Error: El venue con ID %d no fue encontrado.", id)
                ));

        // Actualizar campos
        existingVenue.setName(requestDTO.getName());
        existingVenue.setCountry(requestDTO.getCountry());
        existingVenue.setCity(requestDTO.getCity());
        existingVenue.setLocation(requestDTO.getLocation());
        existingVenue.setStatus(requestDTO.getStatus());

        VenueEntity updatedVenue = venueRepository.save(existingVenue);

        return convertToDTO(updatedVenue);
    }

    @Override
    public void deleteVenue(Long id) {
        if (!venueRepository.existsById(id)) {
            throw new IllegalArgumentException(
                    String.format("Error: El venue con ID %d no fue encontrado.", id)
            );
        }
        venueRepository.deleteById(id);
    }

    @Override
    public List<VenueResponseDTO> getVenuesByCity(String city) {
        return venueRepository.findByCity(city)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ===== MÉTODOS PRIVADOS DE CONVERSIÓN =====

    private VenueEntity convertToEntity(VenueRequestDTO dto) {
        VenueEntity entity = new VenueEntity();
        entity.setName(dto.getName());
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        entity.setLocation(dto.getLocation());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    private VenueResponseDTO convertToDTO(VenueEntity entity) {
        return new VenueResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getCountry(),
                entity.getCity(),
                entity.getLocation(),
                entity.isStatus()
        );
    }
}
