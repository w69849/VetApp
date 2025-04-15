package dev.vetapp.database.entities;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

@DatabaseTable(tableName = "Animals")
public class AnimalEntity {
    @DatabaseField(generatedId = true)
    private int animalId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private ClientEntity owner;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private AnimalTypeEntity animalType;

    @DatabaseField
    private int age;

    @DatabaseField
    private String gender;

    @DatabaseField
    private double weight;

    @DatabaseField
    private String medicalNotes;

    public AnimalEntity() {} // Required by ORMLite
}
