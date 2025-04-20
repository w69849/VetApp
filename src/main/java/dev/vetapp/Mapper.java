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
        entity.setCity(model.getCity());
        entity.setEmail(model.getEmail());
        entity.setAddress(model.getAddress());
        entity.setName(model.getName());
        entity.setSurname(model.getSurname());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setZipCode(model.getZipCode());
        entity.setCreationDate(LocalDate.now());
        return entity;
    }
}
