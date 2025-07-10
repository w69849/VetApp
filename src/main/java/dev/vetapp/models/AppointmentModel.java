package dev.vetapp.models;

import dev.vetapp.services.AppointmentService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentModel {
    private Integer id;
    private String subject;
    private AnimalModel animal;
    private LocalDateTime date;

    public AppointmentModel() {}

    public AppointmentModel(Integer id, String subject, AnimalModel animal, LocalDateTime date) {
        this.id = id;
        this.subject = subject;
        this.animal = animal;
        this.date = date;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public AnimalModel getAnimal() { return animal; }
    public void setAnimal(AnimalModel model) { this.animal = model; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
