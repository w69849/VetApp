package dev.vetapp.database.entities;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.EnumStringType;
import com.j256.ormlite.table.DatabaseTable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@DatabaseTable(tableName = "Appointments")
public class AppointmentEntity {
//    public enum AppointmentStatus{
//        Scheduled,
//        Completed,
//        Cancelled
//    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField()
    private String subject;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private AnimalEntity animal;

    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private LocalDateTime dateTime;

//    @DatabaseField(persisterClass = EnumStringType.class)
//    private AppointmentStatus status;

    public AppointmentEntity() {}

    public AppointmentEntity(String subject, AnimalEntity animal, LocalDateTime date) {
        this.subject = subject;
        this.animal = animal;
        this.dateTime = date;
    }

    public int getId() { return id; }
    public void setId(int appointmentId) { this.id = appointmentId; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public AnimalEntity getAnimal() { return animal; }
    public void setAnimal(AnimalEntity animal) { this.animal = animal; }

    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
}
