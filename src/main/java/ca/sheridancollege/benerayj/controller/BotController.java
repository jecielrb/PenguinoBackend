package ca.sheridancollege.benerayj.controller;

import org.springframework.dao.DataAccessException;
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
	
	/**

    Sends a message to the chatbot service using HTTP POST method and returns a response object.
    @param botRequest A BotRequest object containing the message text and any additional parameters.
    @return A ChatGptResponse object containing the chatbot's response to the given message.
    @throws DataAccessException If there is an error while accessing the chatbot service.
    */
    private final BotServiceImpl botService;

@PostMapping("/send")
public ChatGptResponse sendMessage(@RequestBody BotRequest botRequest) throws DataAccessException {
return botService.askQuestion(botRequest);
}

}
