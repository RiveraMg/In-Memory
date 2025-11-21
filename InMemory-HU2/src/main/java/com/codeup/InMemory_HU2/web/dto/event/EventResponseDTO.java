package com.codeup.InMemory_HU2.web.dto.event;

import com.codeup.InMemory_HU2.entities.EventEntity;
import com.codeup.InMemory_HU2.entities.VenueEntity;
import com.codeup.InMemory_HU2.web.dto.venue.VenueResponseDTO;

import java.time.LocalDateTime;

public class EventResponseDTO {
    private Long id;
    private String name;
    private String hoster;
    private String description;
    private LocalDateTime date;
    private String status;
    private VenueResponseDTO venue;

    // Constructor que recibe EventEntity
    public EventResponseDTO(EventEntity event) {
        this.id = event.getId();
        this.name = event.getName();
        this.hoster = event.getHoster();
        this.description = event.getDescription();
        this.date = event.getDate();
        this.status = event.getStatus().name(); // Convertimos el enum a String

        VenueEntity v = event.getVenue();
        if (v != null) {
            this.venue = new VenueResponseDTO(
                    v.getId(),
                    v.getName(),
                    v.getCountry(),
                    v.getCity(),
                    v.getLocation(),
                    v.isStatus()
            );
        } else {
            this.venue = null;
        }
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getHoster() { return hoster; }
    public String getDescription() { return description; }
    public LocalDateTime getDate() { return date; }
    public String getStatus() { return status; }
    public VenueResponseDTO getVenue() { return venue; }
}
