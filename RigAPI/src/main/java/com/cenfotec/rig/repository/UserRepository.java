package com.cenfotec.rig.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cenfotec.rig.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}