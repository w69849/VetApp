package dev.vetapp.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PetTypes")
public class PetTypeEntity {
    @DatabaseField(generatedId = true)
    private int petTypeId;

    @DatabaseField(canBeNull = false)
    private String species;

    @DatabaseField(canBeNull = false)
    private String breed;

    public PetTypeEntity(String species, String breed) {
        this.species = species;
        this.breed = breed;
    }

    public PetTypeEntity(){}
}
