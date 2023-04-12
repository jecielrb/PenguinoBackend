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
	
	public HttpEntity<ChatGptRequest> buildHttpEntity(ChatGptRequest chatRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
		// headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);

		// Please replace the 2 following lines with the commented line above this once we are about 
		// to make this repo public or once we figured out how to manage keys.
		String apiKey = new String(Base64.decodeBase64(ChatGptConfig.API_KEY_64));
		headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + " " + apiKey);
		
		return new HttpEntity<>(chatRequest, headers);
	}
	
	public ChatGptResponse getResponse(HttpEntity<ChatGptRequest> chatRequestHttpEntity) {
		
		ResponseEntity<ChatGptResponse> responseEntity = restTemplate.postForEntity(
				ChatGptConfig.URL,
				chatRequestHttpEntity,
				ChatGptResponse.class);
		
		return responseEntity.getBody();
	}
	
	public ChatGptResponse askQuestion(BotRequest botRequest) {
		return this.getResponse(
				this.buildHttpEntity(
					new ChatGptRequest(
							ChatGptConfig.MODEL,
							botRequest.getMessage(),
							ChatGptConfig.MAX_TOKEN,
							ChatGptConfig.TEMPERATURE,
							ChatGptConfig.TOP_P)));
	}
}