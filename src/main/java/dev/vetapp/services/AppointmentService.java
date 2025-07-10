package dev.vetapp.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import dev.vetapp.Mapper;
import dev.vetapp.database.DatabaseConnector;
import dev.vetapp.database.entities.AnimalEntity;
import dev.vetapp.database.entities.AnimalTypeEntity;
import dev.vetapp.database.entities.AppointmentEntity;
import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.AppointmentModel;
import javafx.beans.binding.MapExpression;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class AppointmentService {
    private Mapper mapper;

    private ObservableList<AppointmentModel> appointmentList = FXCollections.observableArrayList();
    private ObjectProperty<AnimalModel> selectedAnimal = new SimpleObjectProperty<>();
    private LocalDateTime selectedDateTime;

    public ObjectProperty<AnimalModel> getSelectedAnimalProperty() { return selectedAnimal; }
    public AnimalModel getSelectedAnimal() { return selectedAnimal.get(); }
    public void setSelectedAnimal(AnimalModel model) { this.selectedAnimal.set(model); }

    public AppointmentService() {
        mapper = new Mapper();
    }

    public void SaveAppointment(AppointmentModel model){
        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()) {
            Dao<AnimalTypeEntity, Integer> animalTypeDao = DaoManager.createDao(conn, AnimalTypeEntity.class);
            AnimalTypeEntity animalType = animalTypeDao.queryBuilder()
                    .where().eq("species", model.getAnimal().getSpecies())
                    .and().eq("breed", model.getAnimal().getBreed())
                    .queryForFirst();

            Dao<AppointmentEntity, Integer> appointmentDao = DaoManager.createDao(conn, AppointmentEntity.class);

            AppointmentEntity entity = mapper.mapToEntity(model, animalType);

            Dao<AppointmentEntity, Integer> dao = DaoManager.createDao(conn, AppointmentEntity.class);
            dao.createOrUpdate(entity);

            model.setId(entity.getId());

            appointmentList.add(model);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<AppointmentModel> getAppointments(){
        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()){
            Dao<AppointmentEntity, Integer> dao = DaoManager.createDao(conn, AppointmentEntity.class);

            List<AppointmentEntity> list = dao.queryForAll();

            for(var e : list){
                appointmentList.add(mapper.mapToModel(e));
            }

            return appointmentList;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public LocalDateTime getSelectedDateTime() { return selectedDateTime; }
    public void setSelectedDateTime(LocalDateTime dateTime) { selectedDateTime = dateTime; }

    public ObservableList<AppointmentModel> getAppointmentList() { return appointmentList; }
}
