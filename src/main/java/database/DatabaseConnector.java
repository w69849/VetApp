package database;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import database.entities.*;

import java.io.IOException;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String connectionString =
            "jdbc:sqlserver://localhost;databaseName=MyDb;integratedSecurity=true;";

    private static final String user = "User";
    private static final String password = "Password";

    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnector.class);

    private static ConnectionSource connectionSource;

    public static void initDatabase(){
        initConnection();
        dropTables();
        createTables();
        closeConnection();
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
