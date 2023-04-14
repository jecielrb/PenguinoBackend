package ca.sheridancollege.benerayj.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.benerayj.bean.Pet;
import ca.sheridancollege.benerayj.repository.PetRepository;
import lombok.AllArgsConstructor;

@Component 
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {

	private PetRepository petRepo; 
	
	@Override
	public void run(String... args) throws Exception {
		Pet sample = Pet.builder().name("Sample Pet").age(5).personality("Happy").build();
		petRepo.save(sample);

	}

}
