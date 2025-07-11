package dev.vetapp.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import dev.vetapp.Dialog;
import dev.vetapp.database.entities.AnimalEntity;
import dev.vetapp.database.entities.AnimalTypeEntity;
import dev.vetapp.database.entities.ClientEntity;

import java.sql.SQLException;
import java.time.LocalDate;
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

        ArrayList<ClientEntity> clients = new ArrayList<>(Arrays.asList(
                new ClientEntity(1, "Michał", "Sowa", "msowa@gmail.com", "111222333", "ul. Sucharskiego 2", "22-333", "Rzeszów")
        ));

        ArrayList<AnimalEntity> animals = new ArrayList<>(Arrays.asList(
            new AnimalEntity(clients.get(0), "Zwierze1", types.get(0), LocalDate.now(), "Samiec", 5.0, "")
        ));

        try{
            Dao<AnimalTypeEntity, Integer> dao = DaoManager.createDao(DatabaseConnector.getConnectionSource(), AnimalTypeEntity.class);
            dao.create(types);
        }
        catch (SQLException e){
            logger.warn(e.getMessage());
            Dialog.showError("Nie można załadować rekordów typów zwierząt");
        }

        try{
            Dao<ClientEntity, Integer> dao2 = DaoManager.createDao(DatabaseConnector.getConnectionSource(), ClientEntity.class);
            dao2.create(clients);

            Dao<AnimalEntity, Integer> dao3 = DaoManager.createDao(DatabaseConnector.getConnectionSource(), AnimalEntity.class);
            dao3.create(animals);
        }
        catch(SQLException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
        }
        finally {
            //DatabaseConnector.closeConnection();
        }
    }
}
