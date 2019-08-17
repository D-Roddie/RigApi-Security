package com.cenfotec.rig.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.rig.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
	List<Animal> findAllByAnimalNameContaining(String animalName);
	List<Animal> findAllByBd_Pd_Rig_Name(String rigName);
}