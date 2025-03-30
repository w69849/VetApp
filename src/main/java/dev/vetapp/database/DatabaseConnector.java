package dev.vetapp.database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dev.vetapp.database.entities.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    private static final String connectionString = "jdbc:h2:./data/VetDatabase";
    private static final String user = "User";
    private static final String password = "Password";

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnector.class);

    private static ConnectionSource connectionSource;

    public static void initDatabase(){
        //System.setProperty("ORMLITE_LOG_LEVEL", "DEBUG");
        initConnection();
        //if(!new File("./data/VetDatabase").exists())
            //dropTables();
        //createTables();
        //closeConnection();
    }

    private static void initConnection(){
        try{
            connectionSource = new JdbcConnectionSource(connectionString, user, password);
        }
        catch (SQLException e){
            logger.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource(){
        if(connectionSource == null)
            initConnection();

        return connectionSource;
    }

    public static void closeConnection(){
        if(connectionSource != null){
            try{
                connectionSource.close();
            }
            catch (Exception e){
                logger.warn(e.getMessage());
            }
        }
    }

    private static void createTables(){
        try{
            TableUtils.createTableIfNotExists(connectionSource, AppointmentEntity.class);
            TableUtils.createTableIfNotExists(connectionSource, ClientEntity.class);
            TableUtils.createTableIfNotExists(connectionSource, InventoryEntity.class);
            TableUtils.createTableIfNotExists(connectionSource, PaymentEntity.class);
            TableUtils.createTableIfNotExists(connectionSource, PetEntity.class);
        }
        catch (SQLException e){
            logger.warn(e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(connectionString, user, password);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS PetTypes ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "species VARCHAR(255) NOT NULL"
                    + "breed VARCHAR(255) NOT NULL"
                    + ");";

            stmt.executeUpdate(sql);
            System.out.println("Table created successfully!");

            sql = "INSERT INTO PetTypes(species, breed) VALUES('Pies', 'Mieszany')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Pies', 'Labrador Retriever')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Pies', 'Owczarek niemiecki')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Pies', 'Golden Retriever')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Pies', 'Chihuahua')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Pies', 'Buldog francuski')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Pies', 'Jamnik')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Pies', 'Border Collie')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Pies', 'Yorkshire Terrier')," +
                    "" +
                    "INSERT INTO PetTypes(species, breed) VALUES('Kot', 'Mieszany')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Kot', 'Maine Coon')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Kot', 'Syjamski')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Kot', 'Ragdoll')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Kot', 'Perski')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Kot', 'Sfinks')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Kot', 'Bengalski')," +
                    "" +
                    "INSERT INTO PetTypes(species, breed) VALUES('Królik', 'Mieszany')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Królik', 'Holenderski')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Królik', 'Nowozelandzki')," +
                    "" +
                    "INSERT INTO PetTypes(species, breed) VALUES('Chomik', 'Mieszany')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Chomik', 'Syryjski')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Chomik', 'Roborowski')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Chomik', 'Dżungalski')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Chomik', 'Chiński')," +
                    "" +
                    "INSERT INTO PetTypes(species, breed) VALUES('Świnka morska', 'Peruwiańska')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Świnka morska', 'Rozetka')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Świnka morska', 'Skinny')," +
                    "" +
                    "INSERT INTO PetTypes(species, breed) VALUES('Ptak', 'Kanarek')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Ptak', 'Ara')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Ptak', 'Papużka falista')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Ptak', 'Nimfa')," +
                    "INSERT INTO PetTypes(species, breed) VALUES('Ptak', 'Żako'),";
        }
        catch (SQLException e) {
            logger.warn(e.getMessage());
            //e.printStackTrace();
        }
    }

    private static void dropTables(){
        try{
            TableUtils.dropTable(connectionSource, AppointmentEntity.class, true);
            TableUtils.dropTable(connectionSource, ClientEntity.class, true);
            TableUtils.dropTable(connectionSource, InventoryEntity.class, true);
            TableUtils.dropTable(connectionSource, PaymentEntity.class, true);
            TableUtils.dropTable(connectionSource, PetEntity.class, true);
        }
        catch (SQLException e){
            logger.warn(e.getMessage());
        }
    }
}
