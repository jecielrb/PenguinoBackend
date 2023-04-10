package ca.sheridancollege.benerayj.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BotRequest implements Serializable {
	@NonNull
	private String message;
}
