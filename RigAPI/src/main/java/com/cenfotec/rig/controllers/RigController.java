package com.cenfotec.rig.controllers;

import com.cenfotec.rig.model.Rig;
import com.cenfotec.rig.repository.RigRepository;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/rigs" })
public class RigController {
	private RigRepository repository;

	RigController(RigRepository rigRepository) {
		this.repository = rigRepository;
	}

	@GetMapping
	public List findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Rig> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = "/byName/{name}")
	@ResponseBody
	public List<Rig> findbyName(@PathVariable String name) {
		List<Rig> rigs = repository.findAllByName(name);
		return rigs;
	}
	

	@PostMapping
	public Rig create(@RequestBody Rig rig) {
		return repository.save(rig);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Rig> update(@PathVariable("id") long id, @RequestBody Rig rig) {
		return repository.findById(id).map(record -> {
			record.setName(rig.getName());
			record.setContinent(rig.getContinent());
			record.setLandSurface(rig.getLandSurface());
			record.setSeaSurface(rig.getSeaSurface());
			Rig updated = repository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		return repository.findById(id).map(record -> {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
