package ca.sheridancollege.benerayj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.benerayj.bean.PetOwner;
import ca.sheridancollege.benerayj.repository.PetOwnerRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BackendController {
	
	@Autowired
	private PetOwnerRepository poRepo;
	
	@GetMapping("/")
	public String petOwnerPage(Model model) {
		model.addAttribute("petOwner", new PetOwner());
		model.addAttribute("petOwnerList", poRepo.findAll());
		return "PetOwner.html";
	}
	
	@PostMapping("/createOwner")
	public String createOwner(Model model, @ModelAttribute PetOwner owner) {
		owner.setId(null);
		poRepo.save(owner);
		return "redirect:/";
	}
}
