package com.pet.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.pet.crud.entity.Pet;
import com.pet.crud.service.PetService;

@RestController
@RequestMapping("/petapi/")
@CrossOrigin(origins = "*")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping("/")
    public String home() {
        return "Pet Health Journal API is running 🚀";
    }

    // CREATE
    @PostMapping("/add")
    public ResponseEntity<Pet> addPet(@RequestBody Pet pet) {
        Pet savedPet = petService.addPet(pet);
        return new ResponseEntity<>(savedPet, HttpStatus.CREATED);
    }

    // READ - All
    @GetMapping("/all")
    public ResponseEntity<List<Pet>> getAllPets() {
        List<Pet> pets = petService.getAllPets();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    // READ - By ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPetById(@PathVariable int id) {
        Pet pet = petService.getPetById(id);
        if (pet != null) {
            return new ResponseEntity<>(pet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Pet with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // UPDATE
    @PutMapping("/update")
    public ResponseEntity<?> updatePet(@RequestBody Pet pet) {
        Pet existing = petService.getPetById(pet.getId());
        if (existing != null) {
            Pet updatedPet = petService.updatePet(pet);
            return new ResponseEntity<>(updatedPet, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. Pet with ID " + pet.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePet(@PathVariable int id) {
        Pet existing = petService.getPetById(id);
        if (existing != null) {
            petService.deletePetById(id);
            return new ResponseEntity<>("Pet with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Pet with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
