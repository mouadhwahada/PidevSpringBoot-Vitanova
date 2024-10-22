package tn.spring.pispring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tn.spring.pispring.Entities.Role;
import tn.spring.pispring.Entities.User;
import tn.spring.pispring.Entities.UserRole;
import tn.spring.pispring.Service.UserService;
import tn.spring.pispring.helper.UserFoundException;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PiSpringApplication implements CommandLineRunner {

@Autowired
	private  UserService userService;

@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
public static void main(String[] args) {

=======
@SpringBootApplication
public class PiSpringApplication {

public static void main(String[] args) {

>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2
		SpringApplication.run(PiSpringApplication.class, args);
	}




<<<<<<< HEAD
	@Override
	public void run(String... args) throws Exception {

		try {
			System.out.println("Starting code");


			User user = new User();

			user.setFirstname("Mouadh");
			user.setLastname("Wahada");
			user.setUsername("mouadh18");
			user.setPassword(this.bCryptPasswordEncoder.encode("abc"));
			user.setEmail("abc@gmail.com");
			user.setProfile("default.png");


			Role role1 = new Role();
			role1.setRoleId(44L);
			role1.setRoleName("ADMIN");

			Set<UserRole> userRoleSet = new HashSet<>();

			UserRole userRole = new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);


			userRoleSet.add(userRole);


			User user1 = this.userService.createUser(user, userRoleSet);
			System.out.println(user1.getLastname());
		} catch (UserFoundException e) {
			e.printStackTrace();
		}
	}}
=======

	}
>>>>>>> 3b5791e8482f8840ab7ac5b517100eedf8323db2
