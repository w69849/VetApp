package dev.vetapp.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.EnumStringType;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "Appointments")
public class AppointmentEntity {

    public enum AppointmentStatus{
        Scheduled,
        Completed,
        Cancelled
    }

    @DatabaseField(generatedId = true)
    private int appointmentId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private PetEntity pet;

    @DatabaseField(canBeNull = false)
    private Date dateTime;

    @DatabaseField(persisterClass = EnumStringType.class)
    private AppointmentStatus status;
}
