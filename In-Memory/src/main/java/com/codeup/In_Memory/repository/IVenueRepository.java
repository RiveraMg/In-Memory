package com.codeup.In_Memory.repository;

import com.codeup.In_Memory.domain.Venue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface IVenueRepository {
    // Lista simulada en memoria
    List<Venue> venues = new ArrayList<>();

    // Métodos CRUD
    List<Venue> findAll();

    Optional<Venue> findById(Long id);

    void save(Venue venue);

    void update(Long id, Venue venue);

    void delete(Long id);
}
