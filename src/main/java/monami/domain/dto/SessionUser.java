package monami.domain.dto;

import lombok.Data;
import monami.domain.entity.User;

@Data
public class SessionUser {

	/**
	 * 
	 */
	private String name;
	private String id;
	
	public SessionUser(User user) {
		this.name = user.getName();
		this.id = user.getEmail();
	}
	
	
	
}
