package dev.vetapp.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import dev.vetapp.Mapper;
import dev.vetapp.database.DatabaseConnector;
import dev.vetapp.database.entities.ClientEntity;
import dev.vetapp.models.ClientModel;

public class ClientService {
    private Mapper mapper;

    public void saveClient(ClientModel model)
    {
        ClientEntity entity = mapper.mapToEntity(model);

        try(ConnectionSource conn = DatabaseConnector.getConnectionSource()){
            Dao<ClientEntity, Integer> dao = DaoManager.createDao(conn, ClientEntity.class);

            dao.createOrUpdate(entity);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
