package com.pet.crud.service;

import java.util.List;
import com.pet.crud.entity.Pet;

public interface PetService {
    
    Pet addPet(Pet pet);
    
    List<Pet> getAllPets();
    
    Pet getPetById(int id);
    
    Pet updatePet(Pet pet);
    
    void deletePetById(int id);
}
