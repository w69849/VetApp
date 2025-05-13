package dev.vetapp.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import dev.vetapp.Mapper;
import dev.vetapp.database.DatabaseConnector;
import dev.vetapp.database.entities.ClientEntity;
import dev.vetapp.models.ClientModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ClientService {
    private final Mapper mapper;

    public ClientService(){
        mapper = new Mapper();
    }

    public void saveClient(ClientModel model)
    {
        ClientEntity entity = mapper.mapToEntity(model);

        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()){
            Dao<ClientEntity, Integer> dao = DaoManager.createDao(conn, ClientEntity.class);
            dao.createOrUpdate(entity);
            model.setId(entity.getId());
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ObservableList<ClientModel> getClients(){
        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()){
            Dao<ClientEntity, Integer> dao = DaoManager.createDao(conn, ClientEntity.class);

            List<ClientEntity> list = dao.queryForAll();
            ObservableList<ClientModel> observableList = FXCollections.observableArrayList();

            for(var e : list){
               observableList.add(mapper.mapToModel(e));
            }

            return observableList;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
