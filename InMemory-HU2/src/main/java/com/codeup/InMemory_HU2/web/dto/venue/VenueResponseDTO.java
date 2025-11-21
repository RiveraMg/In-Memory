package com.codeup.InMemory_HU2.web.dto.venue;

public class VenueResponseDTO {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String location;
    private boolean status;

    // Constructor
    public VenueResponseDTO(Long id, String name, String country, String city,
                            String location, boolean status) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.location = location;
        this.status = status;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getLocation() { return location; }
    public boolean isStatus() { return status; }
}
