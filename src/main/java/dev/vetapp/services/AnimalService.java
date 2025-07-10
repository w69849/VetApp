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
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class AnimalService {
    private Mapper mapper;

    private ObservableList<AnimalModel> animalsList = FXCollections.observableArrayList();
    private ObjectProperty<AnimalModel> editedAnimal = new SimpleObjectProperty<>();
    //private ObjectProperty<AnimalModel> selectedAnimal = new SimpleObjectProperty<>();

    public AnimalService(){
        mapper = new Mapper();
    }

    public void SaveAnimal(AnimalModel model, boolean updating){
        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()) {
            Dao<AnimalTypeEntity, Integer> animalTypeDao = DaoManager.createDao(conn, AnimalTypeEntity.class);
            AnimalTypeEntity type = animalTypeDao.queryBuilder()
                    .where().eq("species", model.getSpecies())
                    .and().eq("breed", model.getBreed())
                    .queryForFirst();

            AnimalEntity entity = mapper.mapToEntity(model, type);

            Dao<AnimalEntity, Integer> dao = DaoManager.createDao(conn, AnimalEntity.class);
            dao.createOrUpdate(entity);

            model.setId(entity.getId());

            if(updating) {
                for(int i=0; i < animalsList.size(); i++) {
                    AnimalModel animal = animalsList.get(i);

                    if(animal != null && Objects.equals(animal.getId(), model.getId())) {
                        animalsList.set(i, model);
                    }
                }
            }
            else
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

    public void deleteAnimal(AnimalModel model) {
        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()) {
            Dao<AnimalEntity, Integer> dao = DaoManager.createDao(conn, AnimalEntity.class);

            dao.deleteById(model.getId());
            animalsList.remove(model);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ObjectProperty<AnimalModel> getEditedAnimalProperty() {
        return editedAnimal;
    }
    public AnimalModel getEditedAnimal() {
        return editedAnimal.get();
    }
    public void setEditedAnimal(AnimalModel model) {
        this.editedAnimal.set(model);
    }

//    public ObjectProperty<AnimalModel> getSelectedAnimalProperty() { return selectedAnimal; }
//    public AnimalModel getSelectedAnimal() { return selectedAnimal.get(); }
//    public void setSelectedAnimal(AnimalModel model) { this.selectedAnimal.set(model); }

    public ObservableList<AnimalModel> getAnimalsList() { return animalsList; }
    public void setAnimalsList(ObservableList<AnimalModel> animalsList) { this.animalsList = animalsList; }
}
