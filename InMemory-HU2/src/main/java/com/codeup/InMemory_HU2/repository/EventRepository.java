package com.codeup.InMemory_HU2.repository;

import com.codeup.InMemory_HU2.domain.EventStatus;
import com.codeup.InMemory_HU2.entities.EventEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    // Método para verificar si ya existe un evento con ese nombre
    boolean existsByName(String name);

    // Método para buscar un evento por nombre
    Optional<EventEntity> findByName(String name);

    // NUEVO: Filtrar eventos por ciudad del venue
    @Query("SELECT e FROM EventEntity e WHERE " +
            "(:city IS NULL OR e.venue.city = :city) AND " +
            "(:status IS NULL OR e.status = :status) AND " +
            "(:fechaInicio IS NULL OR e.date >= :fechaInicio)")
    Page<EventEntity> findWithFilters(
            @Param("city") String city,
            @Param("status") EventStatus status,
            @Param("fechaInicio") LocalDateTime fechaInicio,
            Pageable pageable
    );
}