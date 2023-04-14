package ca.sheridancollege.benerayj.services;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ca.sheridancollege.benerayj.bean.BotRequest;
import ca.sheridancollege.benerayj.configuration.ChatGptConfig;
import ca.sheridancollege.benerayj.requests.ChatGptRequest;
import ca.sheridancollege.benerayj.requests.ChatGptResponse;

@Service
public class BotServiceImpl {
	private static RestTemplate restTemplate = new RestTemplate();

	/**
	 * 
	 * Builds an HTTP entity with headers containing the authorization and content
	 * type information for a chatbot request.
	 * 
	 * @param chatRequest A ChatGptRequest object containing the chatbot request
	 *                    parameters.
	 * 
	 * @return An HttpEntity object with the chatbot request and headers.
	 */
	public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
		// headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER +
		// ChatGptConfig.API_KEY);

		// Replace the following 2 lines with the commented line above once the
		// repository is public or the API key is managed.
		String apiKey = new String(Base64.decodeBase64(ChatGptConfig.API_KEY_64));
		headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + " " + apiKey);

		return new HttpEntity<>(chatRequest, headers);
	}

	/**
	 * 
	 * Sends a chatbot request with the provided HttpEntity and returns the
	 * response.
	 * 
	 * @param chatRequestHttpEntity An HttpEntity object with the chatbot request
	 *                              and headers.
	 * 
	 * @return A ChatGptResponse object with the chatbot's response.
	 */
	public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatRequestHttpEntity) {
		ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(ChatGptConfig.URL,
				chatRequestHttpEntity, ChatGptResponse.class);

		return responseEntity.getBody();
	}

	/**
	 * 
	 * Sends a chatbot request with the message from the provided BotRequest object
	 * and returns the response.
	 * 
	 * @param botRequest A BotRequest object containing the message to send to the
	 *                   chatbot.
	 * @return A ChatGptResponse object with the chatbot's response.
	 */
	public ChatGptResponse askQuestion(BotRequest botRequest) {
		return this.getResponse(this.buildHttpEntity(new ChatGptRequest(ChatGptConfig.MODEL, botRequest.getMessage(),
				ChatGptConfig.MAX_TOKEN, ChatGptConfig.TEMPERATURE, ChatGptConfig.TOP_P)));
	}
}