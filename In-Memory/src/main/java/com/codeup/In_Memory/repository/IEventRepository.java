package com.codeup.In_Memory.repository;

import com.codeup.In_Memory.domain.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface IEventRepository {
    // Lista simulada en memoria
    List<Event> events = new ArrayList<>();

    // Métodos CRUD
    List<Event> findAll();

    Optional<Event> findById(Long id);

    void save(Event event);

    void update(Long id, Event event);

    void delete(Long id);
}
