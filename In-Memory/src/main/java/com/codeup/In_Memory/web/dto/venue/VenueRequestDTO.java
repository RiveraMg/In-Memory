package com.codeup.In_Memory.web.dto.venue;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class VenueRequestDTO {

    @NotBlank
    @Size(max = 120)
    private String name;

    @NotBlank
    @Size(max = 120)
    private String country;

    @NotBlank
    @Size(max = 120)
    private String city;

    @NotBlank
    @Size(max = 250)
    private String location;

    @NotNull
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

    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}
