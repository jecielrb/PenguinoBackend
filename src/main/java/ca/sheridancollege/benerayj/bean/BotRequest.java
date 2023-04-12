package ca.sheridancollege.benerayj.bean;

import java.io.Serializable;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BotRequest implements Serializable {
	static final long serialVersionUID = 42L;
	@NonNull
	private String message;
}
