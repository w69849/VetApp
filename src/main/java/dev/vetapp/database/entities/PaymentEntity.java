package dev.vetapp.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.types.EnumStringType;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Payments")
public class PaymentEntity {

    public enum PaymentStatus{
        Paid,
        Overdue
    }

    @DatabaseField(generatedId = true)
    private int paymentId;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private AppointmentEntity appointment;

    @DatabaseField(persisterClass = EnumStringType.class)
    private PaymentStatus status;
}
