package com.codeup.In_Memory.service;

import com.codeup.In_Memory.domain.Event;
import com.codeup.In_Memory.domain.Venue;
import com.codeup.In_Memory.web.dto.event.EventResponseDTO;
import com.codeup.In_Memory.web.dto.venue.VenueResponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CatalogService {

    private final List<Event> events = new ArrayList<>();
    private final List<Venue> venues = new ArrayList<>();
    private Long eventIdCounter = 1L;
    private Long venueIdCounter = 1L;
    private boolean dataLoaded = false; // Evita duplicar los datos

    // =====================
    // CRUD DE VENUES
    // =====================

    public VenueResponseDTO createVenue(String name, String country, String city, String location, boolean status) {
        Venue venue = new Venue(venueIdCounter++, name, country, city, location, status);
        venues.add(venue);
        return new VenueResponseDTO(
                venue.getId(),
                venue.getName(),
                venue.getCountry(),
                venue.getCity(),
                venue.getLocation(),
                venue.isStatus()
        );
    }

    public List<VenueResponseDTO> getAllVenues() {
        List<VenueResponseDTO> result = new ArrayList<>();
        for (Venue v : venues) {
            result.add(new VenueResponseDTO(
                    v.getId(),
                    v.getName(),
                    v.getCountry(),
                    v.getCity(),
                    v.getLocation(),
                    v.isStatus()
            ));
        }
        return result;
    }

    public Optional<Venue> getVenueById(Long id) {
        return venues.stream().filter(v -> v.getId().equals(id)).findFirst();
    }

    public boolean deleteVenue(Long id) {
        return venues.removeIf(v -> v.getId().equals(id));
    }

    // =====================
    // CRUD DE EVENTS
    // =====================

    public EventResponseDTO createEvent(String name, String hoster, String description, String date, String status, Long idVenue) {
        // Obtener el Venue por el id
        Venue venue = getVenueById(idVenue).orElse(null);

        // Si el Venue no se encuentra, el evento no se puede crear, lanzamos una excepción o manejamos el error
        if (venue == null) {
            throw new IllegalArgumentException("El Venue con el id proporcionado no existe.");
        }

        // Crear el evento y asociar el Venue
        Event event = new Event(eventIdCounter++, name, hoster, description, date, status, venue);
        events.add(event);

        return new EventResponseDTO(event); // Retornamos el DTO del evento creado
    }

    public List<EventResponseDTO> getAllEvents() {
        List<EventResponseDTO> result = new ArrayList<>();
        for (Event e : events) {
            result.add(new EventResponseDTO(e));
        }
        return result;
    }

    public Optional<EventResponseDTO> getEventById(Long id) {
        Optional<Event> found = events.stream().filter(e -> e.getId().equals(id)).findFirst();
        return found.map(EventResponseDTO::new);
    }

    public boolean deleteEvent(Long id) {
        return events.removeIf(e -> e.getId().equals(id));
    }

    // =====================
    // DATOS DE EJEMPLO
    // =====================

    public void seedData() {
        if (dataLoaded) return; // Evita duplicar los datos
        dataLoaded = true;

        // Crear lugares
        VenueResponseDTO v1 = createVenue("Estadio Metropolitano", "Colombia", "Barranquilla", "Cra 53 #76-115", true);
        VenueResponseDTO v2 = createVenue("Movistar Arena", "Colombia", "Bogotá", "Cl. 60 #28-47", true);

        // Crear eventos
        createEvent("Rock Fest", "Music World", "Festival de rock con bandas nacionales", "12/12/2025", "Activo", v1.getId());
        createEvent("Pop Night", "Live Events", "Concierto internacional de pop", "20/01/2026", "Pendiente", v2.getId());
    }
}


