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

import com.cenfotec.rig.model.BiologicalDivision;
import com.cenfotec.rig.repository.BiologicalDivisionRepository;

@RestController
@RequestMapping({ "/biologicaldivision" })
public class BiologicalDivisionController {
	private BiologicalDivisionRepository repo;

	BiologicalDivisionController(BiologicalDivisionRepository bdRepository) {
		this.repo = bdRepository;
	}

	@GetMapping
	public List findAll() {
		return repo.findAll();
	}

	@GetMapping(path = { "/{id_bd}" })
	public ResponseEntity<BiologicalDivision> findById(@PathVariable long id_bd) {
		return repo.findById(id_bd).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(path = "/byName/{bdRegion}")
	@ResponseBody
	public List<BiologicalDivision> findbyName(@PathVariable String bdRegion) {
		List<BiologicalDivision> bdDivision = repo.findAllByBdRegionContaining(bdRegion);
		return bdDivision;
	}

	@GetMapping(path = "/byCountry/{rigName}")
	@ResponseBody
	public List<BiologicalDivision> findByRig(@PathVariable String rigName) {
		List<BiologicalDivision> bdDivision = repo.findAllByPd_Rig_Name(rigName);
		return bdDivision;
	}

	@PostMapping
	public BiologicalDivision create(@RequestBody BiologicalDivision biologicaldivision) {
		return repo.save(biologicaldivision);
	}
}
