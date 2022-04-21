package ec.org.inspi.cirev.payload.response;

import java.util.List;

import lombok.Data;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int id;
	private String user;
	private String username;
	private List<String> roles;
	private String codeModulo;
	
	public JwtResponse(String token, int id, String user, String username, List<String> roles, String codeModulo) {
		super();
		this.token = token;
		this.id = id;
		this.user = user;
		this.username = username;
		this.roles = roles;
		this.codeModulo = codeModulo;
	}
	
	

}
