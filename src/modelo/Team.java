package modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {

	private String id;
	
	private String name;
	
	private String country;
	
public Team(String name){
		
		this.name = name;
	}
}
