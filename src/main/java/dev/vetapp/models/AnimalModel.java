package dev.vetapp.models;

import dev.vetapp.database.entities.ClientEntity;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;

public class AnimalModel {
    private Integer id;
    private ClientModel owner;
    private ObjectProperty<String> name = new SimpleObjectProperty<>();
    //private AnimalTypeModel animalType;
    private String species;
    private String breed;
    private LocalDate age;  // in months
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

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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

    public LocalDate getAge() {
        return age;
    }
    public void setAge(LocalDate age) {
        this.age = age;
    }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    //public AnimalTypeModel getAnimalType() {
    //    return animalType;
    //}
    //public void setAnimalType(AnimalTypeModel animalType) {
    //    this.animalType = animalType;
    //}

    public ObjectProperty<String> getNameProperty() { return name; }
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);
    }
}
