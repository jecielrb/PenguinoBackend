package ca.sheridancollege.benerayj.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.benerayj.bean.BotRequest;
import ca.sheridancollege.benerayj.requests.ChatGptResponse;
import ca.sheridancollege.benerayj.services.BotServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/bot")
@RequiredArgsConstructor
public class BotController {
	
private final BotServiceImpl botService;
	@PostMapping("/send")
	public ChatGptResponse sendMessage(@RequestBody BotRequest botRequest) {
		return botService.askQuestion(botRequest);
	}
}
