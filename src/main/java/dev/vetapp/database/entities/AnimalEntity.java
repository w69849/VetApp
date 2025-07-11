package dev.vetapp.database.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@DatabaseTable(tableName = "Animals")
public class AnimalEntity {
    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private ClientEntity owner;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, canBeNull = false)
    private AnimalTypeEntity animalType;

    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private LocalDate birthDate;

    @DatabaseField
    private String gender;

    @DatabaseField
    private double weight;

    @DatabaseField
    private String notes;

    public AnimalEntity(ClientEntity owner, String name, AnimalTypeEntity animalType, LocalDate birthDate,
                        String gender, double weight, String notes) {
        this.owner = owner;
        this.name = name;
        this.animalType = animalType;
        this.birthDate = birthDate;
        this.gender = gender;
        this.weight = weight;
        this.notes = notes;
    }

    public AnimalEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int animalId) {
        this.id = animalId;
    }

    public ClientEntity getOwner() {
        return owner;
    }
    public void setOwner(ClientEntity owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public AnimalTypeEntity getAnimalType() {
        return animalType;
    }
    public void setAnimalType(AnimalTypeEntity animalType) {
        this.animalType = animalType;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
