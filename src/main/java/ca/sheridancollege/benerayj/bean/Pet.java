package ca.sheridancollege.benerayj.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor	
@AllArgsConstructor
@Data
@Document
public class Pet {
	
	@Id
	private String id;
	
	private String personality; 
	private int age;
	
}
