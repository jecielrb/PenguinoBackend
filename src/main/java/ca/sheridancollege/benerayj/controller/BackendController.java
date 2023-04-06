package ca.sheridancollege.benerayj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.benerayj.bean.Pet;
import ca.sheridancollege.benerayj.bean.PetOwner;
import ca.sheridancollege.benerayj.repository.PetOwnerRepository;
import ca.sheridancollege.benerayj.repository.PetRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/pet")
public class BackendController {
	
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
	
	
}
