package com.cenfotec.rig.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.rig.model.PoliticalDivision;
import com.cenfotec.rig.model.Rig;

public interface PoliticalDivisionRepository extends JpaRepository<PoliticalDivision, Long> {
	List<PoliticalDivision> findAllByPdNameContaining(String pdName);
	List<PoliticalDivision> findAllByRig_Name(String rigName);
}
