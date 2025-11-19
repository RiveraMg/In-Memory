package com.codeup.In_Memory.web.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EventRequestDTO {

    @NotBlank
    @Size(max = 120)
    private String name;

    @NotBlank
    @Size(max = 120)
    private String hoster;

    @NotBlank
    @Size(max = 500)
    private String description;

    @NotBlank
    private String date;

    @NotBlank
    private String status;

    @NotNull
    private Long idVenue;

    // Constructor vacío (necesario para la deserialización JSON)
    public EventRequestDTO() {}

    // Constructor completo
    public EventRequestDTO(String name, String hoster, String description, String date, String status, Long idVenue) {
        this.name = name;
        this.hoster = hoster;
        this.description = description;
        this.date = date;
        this.status = status;
        this.idVenue = idVenue;
    }

    // Getters
    public String getName() { return name; }
    public String getHoster() { return hoster; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public Long getIdVenue() { return idVenue; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setHoster(String hoster) { this.hoster = hoster; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(String date) { this.date = date; }
    public void setStatus(String status) { this.status = status; }
    public void setIdVenue(Long idVenue) { this.idVenue = idVenue; }
}
