package com.cenfotec.rig.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cenfotec.rig.model.BiologicalDivision;

@Repository
public interface BiologicalDivisionRepository extends JpaRepository<BiologicalDivision, Long> {
	List<BiologicalDivision> findAllByBdRegionContaining(String bdRegion);
	List<BiologicalDivision> findAllByPd_Rig_Name(String rigName);
}
