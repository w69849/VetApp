package dev.vetapp;

import dev.vetapp.database.entities.AnimalEntity;
import dev.vetapp.database.entities.ClientEntity;
import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.ClientModel;

import java.time.LocalDate;

public class Mapper {
    public AnimalEntity mapToEntity(AnimalModel model){
        AnimalEntity entity = new AnimalEntity();
        entity.setName(model.getName());
        entity.setNotes(model.getNotes());
        entity.setGender(model.getGender());
        entity.setWeight(model.getWeight());
        entity.setBirthDate(model.getBirthDate());
        return entity;
    }

    public ClientEntity mapToEntity(ClientModel model){
        ClientEntity entity = new ClientEntity();

        entity.setEmail(model.getEmail());
        entity.setAddress(model.getAddress());
        entity.setName(model.getName());
        entity.setSurname(model.getSurname());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setCreationDate(LocalDate.now());

        String[] elements = model.getLocation().split(" ");
        entity.setZipCode(elements[0]);
        entity.setCity(elements[1]);

        return entity;
    }

    public AnimalModel mapToModel(AnimalEntity entity){
        AnimalModel model = new AnimalModel();

        return model;
    }

    public ClientModel mapToModel(ClientEntity entity){
        ClientModel model = new ClientModel();

        model.setLocation(entity.getCity(), entity.getZipCode());
        model.setName(entity.getName());
        model.setSurname(entity.getSurname());
        model.setAddress(entity.getAddress());
        model.setEmail(entity.getEmail());

        return model;
    }
}
