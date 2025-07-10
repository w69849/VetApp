package dev.vetapp.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import dev.vetapp.Mapper;
import dev.vetapp.database.DatabaseConnector;
import dev.vetapp.database.entities.ClientEntity;
import dev.vetapp.models.ClientModel;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ClientService {
    private final Mapper mapper;

    public ObservableList<ClientModel> clientsList = FXCollections.observableArrayList();
    private ObjectProperty<ClientModel> selectedClient = new SimpleObjectProperty<>();
    private ObjectProperty<ClientModel> editedClient = new SimpleObjectProperty<>();

    public ClientService(){
        mapper = new Mapper();
    }

    public void saveClient(ClientModel model, boolean updating)
    {
        ClientEntity entity = mapper.mapToEntity(model);

        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()){
            Dao<ClientEntity, Integer> dao = DaoManager.createDao(conn, ClientEntity.class);
            dao.createOrUpdate(entity);
            model.setId(entity.getId());

            if(updating) {
                for(int i=0; i < clientsList.size(); i++) {
                    ClientModel client = clientsList.get(i);

                    if(client != null && Objects.equals(client.getId(), model.getId())) {
                        clientsList.set(i, model);
                    }
                }
            }
            else
                clientsList.add(model);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ObservableList<ClientModel> loadClients(){
        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()){
            Dao<ClientEntity, Integer> dao = DaoManager.createDao(conn, ClientEntity.class);

            List<ClientEntity> list = dao.queryForAll();

            for(var e : list){
               clientsList.add(mapper.mapToModel(e));
            }

            return clientsList;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void deleteClient(ClientModel model) {
        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()) {
            Dao<ClientEntity, Integer> dao = DaoManager.createDao(conn, ClientEntity.class);

            dao.deleteById(model.getId());
            clientsList.remove(model);
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public void UpdateClient(ClientModel model) {
//        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()) {
//            Dao<ClientEntity, Integer> dao = DaoManager.createDao(conn, ClientEntity.class);
//
//            ClientEntity entity = mapper.mapToEntity(model);
//
//            dao.update(entity);
//        }
//        catch(Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    public ObjectProperty<ClientModel> getSelectedClientProperty() {
        return selectedClient;
    }
    public ClientModel getSelectedClient() {
        return selectedClient.get();
    }
    public void setSelectedClient(ClientModel selectedClient) {
        this.selectedClient.set(selectedClient);
    }

    public ObjectProperty<ClientModel> getEditedClientProperty() {
        return editedClient;
    }
    public ClientModel getEditedClient() {
        return editedClient.get();
    }
    public void setEditedClient(ClientModel model) {
        this.editedClient.set(model);
    }
}
