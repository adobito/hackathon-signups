package database.json;

import com.google.gson.Gson;

import database.dto.User;


public class UserJson implements Json {
	private Integer userId;
	private String name;
	private String email;
	private String github;
	private String linkedin;
	private String twitter;
	private String resumePath;
	private String sex;
	private String shirtSize;


	public UserJson() {
	}

	public UserJson(User user) {
		if(user != null) {
			this.userId = user.getId();
			this.name = user.getName();
			this.email = user.getEmail();
			this.github = user.getGithub();
			this.linkedin = user.getLinkedin();
			this.twitter = user.getTwitter();
		}
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGithub() {
		return github;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	
	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getResumePath() {
		return resumePath;
	}
	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getShirtSize() {
		return shirtSize;
	}
	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}

	@Override
	public String toJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}


}
