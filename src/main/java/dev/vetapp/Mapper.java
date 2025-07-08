package dev.vetapp;

import dev.vetapp.database.entities.AnimalEntity;
import dev.vetapp.database.entities.AnimalTypeEntity;
import dev.vetapp.database.entities.ClientEntity;
import dev.vetapp.models.AnimalModel;
import dev.vetapp.models.ClientModel;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;

public class Mapper {
    public AnimalEntity mapToEntity(AnimalModel model, AnimalTypeEntity typeEntity){
        AnimalEntity entity = new AnimalEntity();

        if(model.getId() != null)
            entity.setId(model.getId());

        entity.setName(model.getName());
        entity.setNotes(model.getNotes());
        entity.setGender(model.getGender());
        entity.setWeight(model.getWeight());
        System.out.println("XXDDD3444 " + model.getOwner() + model.getOwner().getName());
        entity.setAnimalType(typeEntity);
        entity.setOwner(mapToEntity(model.getOwner()));

        LocalDateTime birthDate = LocalDateTime.now().minusMonths(model.getAge());
        entity.setBirthDate(Timestamp.valueOf(birthDate));

        return entity;
    }

    public ClientEntity mapToEntity(ClientModel model){
        ClientEntity entity = new ClientEntity();

        System.out.println("XXDDD55555 " + model + model.getName());

        if(model.getId() != null)
            entity.setId(model.getId());

        entity.setEmail(model.getEmail());
        entity.setAddress(model.getAddress());
        entity.setName(model.getName());
        entity.setSurname(model.getSurname());
        entity.setPhoneNumber(model.getPhoneNumber());
        entity.setCreationDate(new Timestamp(System.currentTimeMillis()));

        String[] elements = model.getLocation().split(" ");
        entity.setZipCode(elements[0]);
        entity.setCity(elements[1]);

        return entity;
    }

    public AnimalModel mapToModel(AnimalEntity entity){
        AnimalModel model = new AnimalModel();

        model.setId(entity.getId());
        model.setWeight(entity.getWeight());
        model.setNotes(entity.getNotes());
        model.setGender(entity.getGender());
        model.setName(entity.getName());
        model.setSpecies(entity.getAnimalType().getSpecies());
        model.setBreed(entity.getAnimalType().getBreed());

        LocalDate date = entity.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(date, LocalDate.now());
        int ageInMonths = period.getYears() * 12 + period.getMonths();
        model.setAge(ageInMonths);

        System.out.println("XDDD" + entity.getOwner());
        model.setOwner(mapToModel(entity.getOwner()));

        return model;
    }

    public ClientModel mapToModel(ClientEntity entity){
        ClientModel model = new ClientModel();

        if(entity != null) {
            if(entity.getId() != null)
                model.setId(entity.getId());

            model.setLocation(entity.getCity(), entity.getZipCode());
            model.setName(entity.getName());
            model.setSurname(entity.getSurname());
            model.setAddress(entity.getAddress());
            model.setEmail(entity.getEmail());
            model.setPhoneNumber(entity.getPhoneNumber());
        }


        return model;
    }
}
