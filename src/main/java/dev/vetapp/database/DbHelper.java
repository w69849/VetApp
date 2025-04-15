package dev.vetapp.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import dev.vetapp.database.entities.AnimalTypeEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DbHelper {
    private static final Logger logger = LoggerFactory.getLogger(DbHelper.class);

    public static void FillDatabase()
    {
        ArrayList<AnimalTypeEntity> types = new ArrayList<>(Arrays.asList(
                new AnimalTypeEntity("Pies", "Mieszany"),
                new AnimalTypeEntity("Pies", "Labrador Retriever"),
                new AnimalTypeEntity("Pies", "Owczarek niemiecki"),
                new AnimalTypeEntity("Pies", "Golden Retriever"),
                new AnimalTypeEntity("Pies", "Chihuahua"),
                new AnimalTypeEntity("Pies", "Buldog francuski"),
                new AnimalTypeEntity("Pies", "Jamnik"),
                new AnimalTypeEntity("Pies", "Border Collie"),
                new AnimalTypeEntity("Pies", "Yorkshire Terrier"),

                new AnimalTypeEntity("Kot", "Mieszany"),
                new AnimalTypeEntity("Kot", "Maine Coon"),
                new AnimalTypeEntity("Kot", "Syjamski"),
                new AnimalTypeEntity("Kot", "Ragdoll"),
                new AnimalTypeEntity("Kot", "Perski"),
                new AnimalTypeEntity("Kot", "Sfinks"),
                new AnimalTypeEntity("Kot", "Bengalski"),

                new AnimalTypeEntity("Królik", "Mieszany"),
                new AnimalTypeEntity("Królik", "Holenderski"),
                new AnimalTypeEntity("Królik", "Nowozelandzki"),

                new AnimalTypeEntity("Chomik", "Mieszany"),
                new AnimalTypeEntity("Chomik", "Syryjski"),
                new AnimalTypeEntity("Chomik", "Roborowski"),
                new AnimalTypeEntity("Chomik", "Dżungalski"),
                new AnimalTypeEntity("Chomik", "Chiński"),

                new AnimalTypeEntity("Świnka morska", "Peruwiańska"),
                new AnimalTypeEntity("Świnka morska", "Rozetka"),
                new AnimalTypeEntity("Świnka morska", "Skinny"),

                new AnimalTypeEntity("Ptak", "Kanarek"),
                new AnimalTypeEntity("Ptak", "Ara"),
                new AnimalTypeEntity("Ptak", "Papużka falista"),
                new AnimalTypeEntity("Ptak", "Nimfa"),
                new AnimalTypeEntity("Ptak", "Żako")
        ));

        try{
            Dao<AnimalTypeEntity, Integer> dao = DaoManager.createDao(DatabaseConnector.getConnectionSource(), AnimalTypeEntity.class);
            dao.create(types);
        }
        catch (SQLException e){
            logger.warn(e.getMessage());
        }
        finally {
            //DatabaseConnector.closeConnection();
        }
    }
}
