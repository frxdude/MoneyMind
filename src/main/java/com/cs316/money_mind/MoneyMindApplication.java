package com.cs316.money_mind;

import com.cs316.money_mind.entity.User;
import com.cs316.money_mind.entity.Role;
import com.cs316.money_mind.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.UUID;

@SpringBootApplication
public class MoneyMindApplication {

	UserRepository userRepository;
	PasswordEncoder encoder;
	Environment env;

	@Autowired
	public MoneyMindApplication(UserRepository userRepository, PasswordEncoder encoder, Environment env) {
		this.userRepository = userRepository;
		this.encoder = encoder;
		this.env = env;
	}

	public static void main(String[] args) {
		SpringApplication.run(MoneyMindApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomething() {
		System.out.println(env.getProperty("redis.url"));
		userRepository.save(User.builder()
				.email("B180910040@must.edu.mn")
				.isActive(true)
				.password(encoder.encode("2121"))
				.roles(Collections.singletonList(Role.ROLE_YOUTH))
				.build());
		System.out.println("Done");
	}

}
