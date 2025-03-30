package dev.vetapp.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "Clients")
public class ClientEntity {
    @DatabaseField(generatedId = true)
    private int clientId;

    @DatabaseField(canBeNull = false)
    private String firstName;

    @DatabaseField(canBeNull = false)
    private String lastName;

    @DatabaseField(unique = true, canBeNull = false)
    private String email;

    @DatabaseField(canBeNull = false)
    private String phone;

    @DatabaseField
    private String address;

    @DatabaseField(canBeNull = false)
    private Date creationDate;

    public ClientEntity() {}
}
