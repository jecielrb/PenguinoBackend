package ca.sheridancollege.benerayj.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;

@Data
public class Choice implements Serializable {
	private String text;
	private Integer index;
	@JsonProperty("finish_reason")
	private String finishReason;
}
