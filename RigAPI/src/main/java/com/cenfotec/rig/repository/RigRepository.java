package com.cenfotec.rig.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cenfotec.rig.model.Rig;

@Repository
public interface RigRepository extends JpaRepository<Rig, Long> {
	List<Rig> findAllByName(String name);
	
	
}
