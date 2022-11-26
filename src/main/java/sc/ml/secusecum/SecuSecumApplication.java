package sc.ml.secusecum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sc.ml.secusecum.Config.RsakeysConfig;
import sc.ml.secusecum.Modeles.Personnes;
import sc.ml.secusecum.Modeles.Roles;
import sc.ml.secusecum.Repository.PersonnesRepo;
import sc.ml.secusecum.Repository.RolesRepository;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class SecuSecumApplication implements CommandLineRunner{

	@Autowired
	private PersonnesRepo personnesRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RolesRepository rolesRepository;

	public static void main(String[] args) {
		SpringApplication.run(SecuSecumApplication.class, args);
	}

	//nous allons créer cette methode pour pouvoir encoder les mots de passes
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	@Override
	public void run(String... args) throws Exception {

		Personnes personnes = new Personnes();
		personnes.setUsername("kdiawara");
		personnes.setPassword(passwordEncoder.encode("diawara"));

		String name ="ADMIN";
		Roles role = rolesRepository.findByName(name);

		Set<Roles> rolee = new HashSet<>();
		rolee.add(role);
		personnes.setRoles(rolee);
		personnes.setEmail("karimdiawara96@gmail.com");

		personnesRepo.save(personnes);
	}
}
