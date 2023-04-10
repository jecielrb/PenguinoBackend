package ca.sheridancollege.benerayj.requests;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptRequest implements Serializable {
	
	private String model;
	private String prompt;
	@JsonProperty("max_tokens")
	private Integer maxTokens;
	private Double temperature;
	@JsonProperty("top_p")
	private Double topP;
}

