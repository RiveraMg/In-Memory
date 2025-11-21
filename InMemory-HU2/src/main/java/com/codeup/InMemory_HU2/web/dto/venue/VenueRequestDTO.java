package com.codeup.InMemory_HU2.web.dto.venue;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VenueRequestDTO {

    @NotBlank(message = "El nombre del venue es obligatorio")
    @Size(max = 200, message = "El nombre no puede exceder 200 caracteres")
    private String name;

    @NotBlank(message = "El país es obligatorio")
    @Size(max = 100, message = "El país no puede exceder 100 caracteres")
    private String country;

    @NotBlank(message = "La ciudad es obligatoria")
    @Size(max = 100, message = "La ciudad no puede exceder 100 caracteres")
    private String city;

    @Size(max = 300, message = "La ubicación no puede exceder 300 caracteres")
    private String location;

    @NotNull(message = "El estado es obligatorio")
    private Boolean status;

    // Constructor vacío
    public VenueRequestDTO() {}

    // Constructor completo
    public VenueRequestDTO(String name, String country, String city, String location, Boolean status) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.location = location;
        this.status = status;
    }

    // Getters
    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getLocation() { return location; }
    public Boolean getStatus() { return status; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setCountry(String country) { this.country = country; }
    public void setCity(String city) { this.city = city; }
    public void setLocation(String location) { this.location = location; }
    public void setStatus(Boolean status) { this.status = status; }
}
