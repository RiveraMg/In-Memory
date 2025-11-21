package com.codeup.InMemory_HU2.repository;

import com.codeup.InMemory_HU2.entities.VenueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VenueRepository extends JpaRepository<VenueEntity, Long> {

    // Método para buscar venues por ciudad (útil para filtros)
    List<VenueEntity> findByCity(String city);

    // Método para buscar un venue por nombre
    Optional<VenueEntity> findByName(String name);

    // Método para verificar si existe un venue por nombre
    boolean existsByName(String name);
}