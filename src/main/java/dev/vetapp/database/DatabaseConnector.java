package dev.vetapp.database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dev.vetapp.database.entities.*;

import java.io.File;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String connectionString = "jdbc:h2:./data/VetDatabase";
    private static final String user = "User";
    private static final String password = "Password";

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnector.class);

    private static ConnectionSource connectionSource;

    public static void initDatabase(){
        initConnection();

//       if(new File("./data/VetDatabase.mv.db").exists()) {
        //           dropTables();
//        }

        createTables();
        DbHelper.FillDatabase();
        closeConnection();
    }

    private static void initConnection(){
        try{
            connectionSource = new JdbcConnectionSource(connectionString, user, password);
        }
        catch (SQLException e){
            logger.warn(e.getMessage());
            e.printStackTrace();
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
                e.printStackTrace();
            }
        }
    }

    private static void createTables(){
        try{
            TableUtils.createTableIfNotExists(connectionSource, AppointmentEntity.class);
            TableUtils.createTableIfNotExists(connectionSource, ClientEntity.class);
            TableUtils.createTableIfNotExists(connectionSource, AnimalEntity.class);
            TableUtils.createTableIfNotExists(connectionSource, AnimalTypeEntity.class);
        }
        catch (SQLException e){
            logger.warn("FAIL AT CREATING TABLES" + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void dropTables(){
        try{
            TableUtils.dropTable(connectionSource, AppointmentEntity.class, true);
            TableUtils.dropTable(connectionSource, ClientEntity.class, true);
            TableUtils.dropTable(connectionSource, AnimalEntity.class, true);
            TableUtils.dropTable(connectionSource, AnimalTypeEntity.class, true);
        }
        catch (SQLException e){
            logger.warn(e.getMessage());
        }
    }
}
