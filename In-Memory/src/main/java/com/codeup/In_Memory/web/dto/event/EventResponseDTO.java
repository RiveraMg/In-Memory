package com.codeup.In_Memory.web.dto.event;

import com.codeup.In_Memory.domain.Event;
import com.codeup.In_Memory.domain.Venue;
import com.codeup.In_Memory.web.dto.venue.VenueResponseDTO;

public class EventResponseDTO {
    private Long id;
    private String name;
    private String hoster;
    private String description;
    private String date;
    private String status;
    private VenueResponseDTO venue; // el objeto completo del venue

    public EventResponseDTO(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.hoster = event.getHoster();
        this.description = event.getDescription();
        this.date = event.getDate();
        this.status = event.getStatus();

        Venue v = event.getVenue();
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
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public VenueResponseDTO getVenue() { return venue; }
}

