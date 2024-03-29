package com.cenfotec.rig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.cenfotec.rig.model.User;
import com.cenfotec.rig.repository.RigRepository;
import com.cenfotec.rig.repository.UserRepository;
import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

	@Autowired
	RigRepository contacts;

	@Autowired
	UserRepository users;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
		// log.debug("initializing contacts data...");
		// Arrays.asList("Luis", "Fran").forEach(v ->
		// this.contacts.saveAndFlush(Contact.builder().name(v).build()));

		// log.debug("printing all contacts...");
		// this.contacts.findAll().forEach(v -> log.debug(" Contact :" + v.toString()));

		this.users.save(User.builder().username("user").password(this.passwordEncoder.encode("password"))
				.roles(Arrays.asList("ROLE_USER")).build());

		this.users.save(User.builder().username("admin").password(this.passwordEncoder.encode("password"))
				.roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN")).build());

		log.debug("printing all users...");
		this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
	}
}