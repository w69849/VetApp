package dev.vetapp.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import dev.vetapp.Mapper;
import dev.vetapp.database.DatabaseConnector;
import dev.vetapp.database.entities.AnimalEntity;
import dev.vetapp.database.entities.AnimalTypeEntity;
import dev.vetapp.database.entities.ClientEntity;
import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class AnimalService {
    Mapper mapper;

    ObservableList<AnimalModel> animalsList = FXCollections.observableArrayList();

    public AnimalService(){
        mapper = new Mapper();
    }

    public void SaveAnimal(AnimalModel model){
        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()) {
            Dao<AnimalTypeEntity, Integer> animalTypeDao = DaoManager.createDao(conn, AnimalTypeEntity.class);
            AnimalTypeEntity type = animalTypeDao.queryBuilder()
                    .where().eq("species", model.getSpecies())
                    .and().eq("breed", model.getBreed())
                    .queryForFirst();

//            System.out.println("XXDDD33 " + model.getOwner() + model.getOwner().getName());

            AnimalEntity entity = mapper.mapToEntity(model, type);

            Dao<AnimalEntity, Integer> dao = DaoManager.createDao(conn, AnimalEntity.class);
            dao.createOrUpdate(entity);

            model.setId(entity.getId());
            animalsList.add(model);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<AnimalModel> getAnimals(){
        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()){
            Dao<AnimalEntity, Integer> dao = DaoManager.createDao(conn, AnimalEntity.class);

            List<AnimalEntity> list = dao.queryForAll();

            for(var e : list){
                animalsList.add(mapper.mapToModel(e));
                System.out.println("XDDDaaaa " + e.getNotes());
            }

            return animalsList;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
