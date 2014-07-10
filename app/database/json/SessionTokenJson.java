package database.json;

import com.google.gson.Gson;

import database.dto.LoginSession;

public class SessionTokenJson {

	private String token;

	
	public SessionTokenJson() {
		super();
	}
	
	public SessionTokenJson(LoginSession loginSession) {
		this.token = loginSession.getToken();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	
}
