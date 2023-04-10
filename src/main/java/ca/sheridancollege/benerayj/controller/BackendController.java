package ca.sheridancollege.benerayj.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.benerayj.bean.BotRequest;
import ca.sheridancollege.benerayj.bean.Pet;
import ca.sheridancollege.benerayj.bean.PetOwner;
import ca.sheridancollege.benerayj.configuration.ChatGptConfig;
import ca.sheridancollege.benerayj.repository.PetOwnerRepository;
import ca.sheridancollege.benerayj.repository.PetRepository;
import ca.sheridancollege.benerayj.requests.ChatGptResponse;
import ca.sheridancollege.benerayj.services.BotServiceImpl;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pet")
public class BackendController {
	private static final String[] SUGGEST_NAME = {
		"give me a cute name for a penguin robot, use only one word please", 
		"give me a one word name for a robot penguin arduino pet", 
		"give me a one word name for a penguin", 
		"give me a one word name for a robot pet", 
		"give me a one word name for a penguin pet"};
	
	@Autowired
	private PetOwnerRepository ownerRepo;
	
	@Autowired
	private PetRepository petRepo;
	
	@GetMapping(value={"/", ""})
	public List<Pet> pet(Model model) {
		return petRepo.findAll();
	}
	
	@PostMapping(value={"/", ""}, headers="Content-type=application/json")
	public String createPet(@RequestBody Pet pet) {
		Pet p = petRepo.save(pet);
		return "Record added at index " + p.getId();
	}
	
	private final BotServiceImpl botService;

	@PostMapping("/ask")
	public String[] sendMessage(@RequestBody BotRequest botRequest) {
		String[] question = {"give me a cute name for a penguin robot, use only one word please", "give me a one word name for a robot penguin arduino pet", "give me a one word name for a penguin", "give me a one word name for a robot pet", "give me a one word name for a penguin pet"};
		String[] names = new String[5]; 
		for(int i = 0; i < 5; i++) {
			botRequest.setMessage(question[i]);
			names[i] = botService.askQuestion(botRequest).getChoices().toString();
		}
		return names;
		
	}

	@GetMapping("/suggestNames/{size}")
	public List<String> suggestNames(@PathVariable(name = "size") int suggestionSize) {
		List<String> names = new ArrayList<>(suggestionSize); 
		for (String question : SUGGEST_NAME) {
			BotRequest request = new BotRequest(question);

			// Formatting string from here. Pls dont modify.
			String name = botService.askQuestion(request).getChoices().get(0).getText().trim();
			int invalidCharIdx = name.indexOf('.', 0);
			if (invalidCharIdx > 0 && invalidCharIdx == name.lastIndexOf('.')) {
				name = name.substring(0, name.length() - 1);	
			}
			names.add(name);
		}
		return names;
	}
}
