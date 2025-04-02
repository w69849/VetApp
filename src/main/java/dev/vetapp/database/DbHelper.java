package dev.vetapp.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import dev.vetapp.database.entities.PetTypeEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class DbHelper {
    private static final Logger logger = LoggerFactory.getLogger(DbHelper.class);

    public static void FillDatabase()
    {
        ArrayList<PetTypeEntity> types = new ArrayList<>(Arrays.asList(
                new PetTypeEntity("Pies", "Mieszany"),
                new PetTypeEntity("Pies", "Labrador Retriever"),
                new PetTypeEntity("Pies", "Owczarek niemiecki"),
                new PetTypeEntity("Pies", "Golden Retriever"),
                new PetTypeEntity("Pies", "Chihuahua"),
                new PetTypeEntity("Pies", "Buldog francuski"),
                new PetTypeEntity("Pies", "Jamnik"),
                new PetTypeEntity("Pies", "Border Collie"),
                new PetTypeEntity("Pies", "Yorkshire Terrier"),

                new PetTypeEntity("Kot", "Mieszany"),
                new PetTypeEntity("Kot", "Maine Coon"),
                new PetTypeEntity("Kot", "Syjamski"),
                new PetTypeEntity("Kot", "Ragdoll"),
                new PetTypeEntity("Kot", "Perski"),
                new PetTypeEntity("Kot", "Sfinks"),
                new PetTypeEntity("Kot", "Bengalski"),

                new PetTypeEntity("Królik", "Mieszany"),
                new PetTypeEntity("Królik", "Holenderski"),
                new PetTypeEntity("Królik", "Nowozelandzki"),

                new PetTypeEntity("Chomik", "Mieszany"),
                new PetTypeEntity("Chomik", "Syryjski"),
                new PetTypeEntity("Chomik", "Roborowski"),
                new PetTypeEntity("Chomik", "Dżungalski"),
                new PetTypeEntity("Chomik", "Chiński"),

                new PetTypeEntity("Świnka morska", "Peruwiańska"),
                new PetTypeEntity("Świnka morska", "Rozetka"),
                new PetTypeEntity("Świnka morska", "Skinny"),

                new PetTypeEntity("Ptak", "Kanarek"),
                new PetTypeEntity("Ptak", "Ara"),
                new PetTypeEntity("Ptak", "Papużka falista"),
                new PetTypeEntity("Ptak", "Nimfa"),
                new PetTypeEntity("Ptak", "Żako")
        ));

        try{
            Dao<PetTypeEntity, Integer> dao = DaoManager.createDao(DatabaseConnector.getConnectionSource(), PetTypeEntity.class);
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
