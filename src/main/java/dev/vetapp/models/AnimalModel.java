package dev.vetapp.models;

import dev.vetapp.database.entities.ClientEntity;

import java.time.LocalDate;

public class AnimalModel {
    private ClientModel owner;
    private String name;
    private AnimalTypeModel animalType;
    private LocalDate birthDate;
    private String gender;
    private double weight;
    private String notes;

//    public AnimalModel(ClientModel owner, String name, AnimalTypeModel animalType, Date birthDate,
//                       String gender, double weight, String notes){
//        this.owner = owner;
//        this.name = name;
//        this.animalType = animalType;
//        this.birthDate = birthDate;
//        this.gender = gender;
//        this.weight = weight;
//        this.notes = notes;
//    }
    public AnimalModel(){}

    public ClientModel getOwner() {
        return owner;
    }
    public void setOwner(ClientModel owner) {
        this.owner = owner;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public AnimalTypeModel getAnimalType() {
        return animalType;
    }
    public void setAnimalType(AnimalTypeModel animalType) {
        this.animalType = animalType;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
