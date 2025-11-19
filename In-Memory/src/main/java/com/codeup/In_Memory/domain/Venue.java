package com.codeup.In_Memory.domain;

public class Venue {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String location;
    private boolean status;

    // Constructor vacío
    public Venue() {
    }

    // Constructor
    public Venue(Long id, String name, String country, String city, String location, boolean status) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.location = location;
        this.status = status;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }
}
