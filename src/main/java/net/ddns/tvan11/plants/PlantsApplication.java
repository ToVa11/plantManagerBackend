package net.ddns.tvan11.plants;

import net.ddns.tvan11.plants.domain.User;
import net.ddns.tvan11.plants.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static net.ddns.tvan11.plants.enumeration.Role.ROLE_SUPER_ADMIN;

@SpringBootApplication
public class PlantsApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(PlantsApplication.class, args);

	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CommandLineRunner CommandLineRunner() {
		return args -> {
			User user = new User();
			user.setFirstName("Tom");
			user.setLastName("Vanelven");
			user.setEmail("tom.vanelven@hotmail.com");
			user.setUsername("tvan11");
			user.setAuthorities(ROLE_SUPER_ADMIN.getAuthorities());
			user.setRoles(new String[]{ROLE_SUPER_ADMIN.name()});
			user.setPassword("$2y$12$lspwa8mIhda8bwW4cD/QnuLsjoLdF8JbLdmVCDZD7E5ncmvms8Ev2");
			userRepository.save(user);
		};
	};
}
