package com.codeup.In_Memory.domain;


public class Event {
    private Long id;
    private String name;
    private String hoster;
    private String description;
    private String date;
    private String status;
    private Venue venue; // ← ahora el evento tiene el objeto Venue directamente

    // Constructor completo
    public Event(Long id, String name, String hoster, String description, String date, String status, Venue venue) {
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

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Venue getVenue() { return venue; }
    public void setVenue(Venue venue) { this.venue = venue; }


    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hoster='" + hoster + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
