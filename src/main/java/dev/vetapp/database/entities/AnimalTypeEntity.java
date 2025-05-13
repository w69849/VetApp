package dev.vetapp.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "AnimalTypes")
public class AnimalTypeEntity {
    @DatabaseField(generatedId = true)
    private int animalTypeId;

    @DatabaseField(canBeNull = false)
    private String species;

    @DatabaseField(canBeNull = false)
    private String breed;

    public AnimalTypeEntity(String species, String breed) {
        this.species = species;
        this.breed = breed;
    }

    public AnimalTypeEntity(){}

    public String getSpecies(){
        return species;
    }

    public String getBreed(){
        return breed;
    }
}
