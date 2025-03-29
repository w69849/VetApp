package dev.michal.vetapp.database.entities;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Inventory")
public class InventoryEntity {
    @DatabaseField(generatedId = true)
    private int itemId;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private int quantity;
}
