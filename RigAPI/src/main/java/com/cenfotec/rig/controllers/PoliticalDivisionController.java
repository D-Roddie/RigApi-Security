package com.cenfotec.rig.controllers;

import java.util.List;
import java.util.Optional;

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

import com.cenfotec.rig.model.Animal;
import com.cenfotec.rig.model.PoliticalDivision;
import com.cenfotec.rig.model.Rig;
import com.cenfotec.rig.repository.PoliticalDivisionRepository;
import com.cenfotec.rig.repository.RigRepository;

@RestController
@RequestMapping({ "/politicaldivision" })
public class PoliticalDivisionController {

	private PoliticalDivisionRepository repo;
	private RigRepository rigRepo;

	PoliticalDivisionController(PoliticalDivisionRepository pdRepository) {
		this.repo = pdRepository;
	}

	@GetMapping
	public List findAll() {
		return repo.findAll();
	}

	@GetMapping(path = { "/{id_pd}" })
	public ResponseEntity<PoliticalDivision> findById(@PathVariable long id_pd) {
		return repo.findById(id_pd).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping(path = "/byName/{pdName}")
	@ResponseBody
	public List<PoliticalDivision> findbyName(@PathVariable String pdName) {
		List<PoliticalDivision> politicalDiv = repo.findAllByPdNameContaining(pdName);
		return politicalDiv;
	}
	
	@GetMapping(path = "/byCountry/{rigName}")
	@ResponseBody
	public List<PoliticalDivision> findbyCountry(@PathVariable String rigName) {
		List<PoliticalDivision> politicalDiv = repo.findAllByRig_Name(rigName);
		return politicalDiv;
	}

	@PostMapping
	public PoliticalDivision create(@RequestBody PoliticalDivision politicaldivision) {
		return repo.save(politicaldivision);
	}

	@PutMapping(value = "/{id_pd}")
	public ResponseEntity<PoliticalDivision> update(@PathVariable("id_pd") long id_pd,
			@RequestBody PoliticalDivision politicaldivision) {

		return repo.findById(id_pd).map(record -> {
			record.setPdName(politicaldivision.getPdName());
			PoliticalDivision updated = repo.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id_pd}" })
	public ResponseEntity<?> delete(@PathVariable("id_pd") long id_pd) {
		return repo.findById(id_pd).map(record -> {
			repo.deleteById(id_pd);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
