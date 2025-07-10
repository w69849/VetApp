package dev.vetapp.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ClientModel {
    private Integer id;
    private ObjectProperty<String> name = new SimpleObjectProperty<>();
    private ObjectProperty<String> surname = new SimpleObjectProperty<>();
    private String email;
    private String phoneNumber;
    private String address;
    private String location;
    //private String zipCode;
    //private String city;

    public ClientModel(String name, String surname, String email, String phoneNumber,
                       String address, String location){
        this.name.set(name);
        this.surname.set(surname);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.location = location;
    }
    public ClientModel(){}

    public Integer getId(){ return id; }
    public void setId(Integer id){ this.id = id; }

    public ObjectProperty<String> getNameProperty() { return name; }
    public String getName() {
        return name.get();
    }
    public void setName(String name) { this.name.set(name); }

    public ObjectProperty<String> getSurnameProperty() { return surname; }
    public String getSurname() {
        return surname.get();
    }
    public void setSurname(String surname) { this.surname.set(surname); }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation(){
        return location;
    }
    public void setLocation(String city, String zip)
    {
        if(city != null)
            location = city + " " + zip;
    }

//    public String getZipCode() {
//        return zipCode;
//    }
//
//    public void setZipCode(String zipCode) {
//        this.zipCode = zipCode;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }


    @Override
    public String toString() {
        return name.get() + " " + surname.get();
    }
}
