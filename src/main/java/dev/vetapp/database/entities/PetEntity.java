package dev.vetapp.database.entities;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

@DatabaseTable(tableName = "Pets")
public class PetEntity {
    @DatabaseField(generatedId = true)
    private int petId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ClientEntity owner;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private String species;

    @DatabaseField
    private String breed;

    @DatabaseField
    private int age;

    @DatabaseField
    private String gender;

    @DatabaseField
    private double weight;

    @DatabaseField
    private String medicalNotes;

    public PetEntity() {} // Required by ORMLite
}
