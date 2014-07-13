package database.json;

import com.google.gson.Gson;

import database.dto.UserInfo;


public class UserInfoJson {
	private Integer userId;
	private String name;
	private String github;
	private String linkedin;
	private String resumePath;
	private String sex;
	private String shirtSize;


	public UserInfoJson() {
	}

	public UserInfoJson(UserInfo userInfo) {
		if(userInfo != null) {
			if(userInfo.getUser() != null) {
				this.userId = userInfo.getUser().getUserId();
			}
			this.name = userInfo.getName();
			this.github = userInfo.getGithub();
			this.linkedin = userInfo.getLinkedin();
			if(userInfo.getResume() != null) {
				this.resumePath = userInfo.getResume().getPath();
			}
			if(userInfo.getSex() != null) {
				this.sex = userInfo.getSex().getName();
			}
			if(userInfo.getShirtSize() != null) {
				this.shirtSize = userInfo.getShirtSize().getName();
			}
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
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}


}
