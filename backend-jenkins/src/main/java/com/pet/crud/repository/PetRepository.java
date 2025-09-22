package com.pet.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pet.crud.entity.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    
    Pet findByOwnerEmail(String ownerEmail);
    Pet findByOwnerContact(String ownerContact);
}
