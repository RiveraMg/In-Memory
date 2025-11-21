package com.codeup.InMemory_HU2.entities;

import com.codeup.InMemory_HU2.domain.EventStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "events")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 200)
    private String name;

    @Column(nullable = false, length = 100)
    private String hoster;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EventStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id", nullable = false)
    private VenueEntity venue;

    // Constructor vacío (obligatorio para JPA)
    public EventEntity() {
    }

    // Constructor completo
    public EventEntity(Long id, String name, String hoster, String description, LocalDateTime date, EventStatus status, VenueEntity venue) {
        this.id = id;
        this.name = name;
        this.hoster = hoster;
        this.description = description;
        this.date = date;
        this.status = status;
        this.venue = venue;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getHoster() { return hoster; }
    public void setHoster(String hoster) { this.hoster = hoster; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public EventStatus getStatus() { return status; }
    public void setStatus(EventStatus status) { this.status = status; }

    public VenueEntity getVenue() { return venue; }
    public void setVenue(VenueEntity venue) { this.venue = venue; }

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hoster='" + hoster + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}