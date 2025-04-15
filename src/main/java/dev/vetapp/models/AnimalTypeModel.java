package dev.vetapp.models;

public class AnimalTypeModel {
    private String species;
    private String breed;

    public AnimalTypeModel(String species, String breed){
        this.species = species;
        this.breed = breed;
    }

    public String getSpecies(){
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
}
