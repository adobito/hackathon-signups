package database.json;

import com.google.gson.Gson;


public class RegisterJson implements Json {

	private String email;
	private String password;
	
	public RegisterJson() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	
}
