package com.codeup.InMemory_HU2.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venues")
public class VenueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false, length = 100)
    private String country;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(length = 300)
    private String location;

    @Column(nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "venue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EventEntity> events = new ArrayList<>();

    // Constructor vacío
    public VenueEntity() {
    }

    // Constructor completo sin events
    public VenueEntity(Long id, String name, String country, String city, String location, boolean status) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.location = location;
        this.status = status;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public List<EventEntity> getEvents() { return events; }
    public void setEvents(List<EventEntity> events) { this.events = events; }

    @Override
    public String toString() {
        return "VenueEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", status=" + status +
                '}';
    }
}
