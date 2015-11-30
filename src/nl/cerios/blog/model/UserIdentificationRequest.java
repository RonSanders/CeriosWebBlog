package nl.cerios.blog.model;

public final class UserIdentificationRequest {// rename to: UserIndentificationRequest ?
	private String USERNAME;
	private String 	PASSWORD	;
	public UserIdentificationRequest(String username, String password){
		USERNAME = username;
		PASSWORD = 	password;
	}
	
	public String getUsername() {
		return USERNAME;
	}
	public String getPassword() {
		return PASSWORD;
	}
}
