package com.pet.crud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pet_table")
public class Pet {
    
    @Id
    @Column(name = "pet_id")
    private int id;

    @Column(name = "pet_name", nullable = false, length = 50)
    private String name;

    @Column(name = "pet_species", nullable = false, length = 20)
    private String species; // Dog, Cat, Bird, etc.

    @Column(name = "pet_breed", length = 50)
    private String breed; // Labrador, Persian, etc. (optional)

    @Column(name = "pet_age", nullable = false)
    private int age; // in years

    @Column(name = "pet_gender", length = 10)
    private String gender; // Male, Female

    @Column(name = "owner_name", nullable = false, length = 50)
    private String ownerName;

    @Column(name = "owner_contact", nullable = false, unique = true, length = 20)
    private String ownerContact;

    @Column(name = "owner_email", nullable = false, unique = true, length = 50)
    private String ownerEmail;

    // --- Getters and Setters ---

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerContact() {
        return ownerContact;
    }
    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    @Override
    public String toString() {
        return "Pet [id=" + id + ", name=" + name + ", species=" + species + ", breed=" + breed
                + ", age=" + age + ", gender=" + gender + ", ownerName=" + ownerName
                + ", ownerContact=" + ownerContact + ", ownerEmail=" + ownerEmail + "]";
    }
}
