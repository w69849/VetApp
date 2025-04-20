package dev.vetapp.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import dev.vetapp.database.DatabaseConnector;
import dev.vetapp.database.entities.AnimalTypeEntity;
import dev.vetapp.models.AnimalTypeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AnimalTypeService {
    public ObservableList<AnimalTypeModel> animalTypes;
    public ObservableList<String> animalSpecies;
    private ObservableList<String> animalBreeds;

    public AnimalTypeService(){
        readAnimalTypes();
    }

    private void readAnimalTypes(){
        try(ConnectionSource connectionSource = DatabaseConnector.getConnectionSource()) {
            Dao<AnimalTypeEntity, Integer> dao = DaoManager.createDao(connectionSource, AnimalTypeEntity.class);
            List<AnimalTypeEntity> list = dao.queryForAll();

            animalTypes = FXCollections.observableArrayList(list.stream()
                    .map(item -> new AnimalTypeModel(item.getSpecies(), item.getBreed()))
                    .collect(Collectors.toList()));

            animalSpecies = FXCollections.observableArrayList(animalTypes.stream()
                    .map(AnimalTypeModel::getSpecies)
                            .distinct()
                    .collect(Collectors.toList()));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<String> getBreeds(String species){
        animalBreeds = FXCollections.observableArrayList(animalTypes.stream()
                .filter(e -> Objects.equals(e.getSpecies(), species))
                .map(AnimalTypeModel::getBreed)
                .collect(Collectors.toList()));

        return animalBreeds;
    }

    public AnimalTypeModel getAnimalType(String species, String breed){
        return animalTypes.stream()
                .filter(e -> Objects.equals(e.getSpecies(), species)
                        && Objects.equals(e.getBreed(), breed))
                .findFirst()
                .orElse(null);
    }
}
