package com.cenfotec.rig.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cenfotec.rig.model.Animal;
import com.cenfotec.rig.repository.AnimalRepository;
import com.cenfotec.rig.repository.RigRepository;

@RestController
@RequestMapping({ "/animal" })
public class AnimalController {
	private AnimalRepository repo;
	private RigRepository rigRepo;

	AnimalController(AnimalRepository animalRepository) {
		this.repo = animalRepository;
	}

	@GetMapping
	public List findAll() {
		return repo.findAll();
	}

	@GetMapping(path = { "/{id_animal}" })
	public ResponseEntity<Animal> findById(@PathVariable long id_animal) {
		return repo.findById(id_animal).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = "/byName/{animalName}")
	@ResponseBody
	public List<Animal> findbyName(@PathVariable String animalName) {
		List<Animal> animals = repo.findAllByAnimalNameContaining(animalName);
		return animals;
	}

	@GetMapping(path = "/byRig/{rigName}")
	@ResponseBody
	public List<Animal> findByRig(@PathVariable String rigName) {
		List<Animal> animals = repo.findAllByBd_Pd_Rig_Name(rigName);
		return animals;
	}

	@PostMapping
	public Animal create(@RequestBody Animal animal) {
		return repo.save(animal);
	}
}
