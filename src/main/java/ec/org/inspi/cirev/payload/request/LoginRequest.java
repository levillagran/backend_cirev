package ec.org.inspi.cirev.payload.request;

import lombok.Data;

@Data
public class LoginRequest {

	private String username;
	private String password;
	private String module;

}
