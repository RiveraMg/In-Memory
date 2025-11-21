package com.codeup.InMemory_HU2.web.dto.event;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class EventRequestDTO {

    @NotBlank(message = "El nombre del evento es obligatorio")
    @Size(max = 400, message = "El nombre no puede exceder 400 caracteres")
    private String name;

    @NotBlank(message = "El organizador es obligatorio")
    @Size(max = 200, message = "El organizador no puede exceder 200 caracteres")
    private String hoster;

    @Size(max = 1500, message = "La descripción no puede exceder 1500 caracteres")
    private String description;

    @NotNull(message = "La fecha del evento es obligatoria")
    @Future(message = "La fecha del evento debe ser futura")
    private LocalDateTime date;

    @NotNull(message = "El estado del evento es obligatorio")
    private String status; // Recibimos como String y lo convertimos a EventStatus en el Service

    @NotNull(message = "El ID del venue es obligatorio")
    private Long idVenue;

    // Constructor vacío
    public EventRequestDTO() {}

    // Constructor completo
    public EventRequestDTO(String name, String hoster, String description,
                           LocalDateTime date, String status, Long idVenue) {
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
    public LocalDateTime getDate() { return date; }
    public String getStatus() { return status; }
    public Long getIdVenue() { return idVenue; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setHoster(String hoster) { this.hoster = hoster; }
    public void setDescription(String description) { this.description = description; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public void setStatus(String status) { this.status = status; }
    public void setIdVenue(Long idVenue) { this.idVenue = idVenue; }
}