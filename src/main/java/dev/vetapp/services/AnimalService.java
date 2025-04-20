package dev.vetapp.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import dev.vetapp.Mapper;
import dev.vetapp.database.DatabaseConnector;
import dev.vetapp.database.entities.AnimalEntity;
import dev.vetapp.database.entities.AnimalTypeEntity;
import dev.vetapp.models.AnimalModel;

import java.sql.SQLException;

public class AnimalService {
    Mapper mapper = new Mapper();

    public void SaveAnimal(AnimalModel animal){
        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()) {
            Dao<AnimalEntity, Integer> dao = DaoManager.createDao(conn, AnimalEntity.class);

            
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
