package com.coderli.entity;

public class ConfigInfo {
	private String driver;
	private String url;
	private String name;
	private String password;
	private String generatePath;
	private String targetBasePackage;

	public ConfigInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getTargetBasePackage() {
		return targetBasePackage;
	}

	public void setTargetBasePackage(String targetBasePackage) {
		this.targetBasePackage = targetBasePackage;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGeneratePath() {
		return generatePath;
	}

	public void setGeneratePath(String generatePath) {
		this.generatePath = generatePath;
	}

	@Override
	public String toString() {
		return "ConfigInfo [driver=" + driver + ", url=" + url + ", name="
				+ name + ", password=" + password + ", generatePath="
				+ generatePath + ", targetBasePackage=" + targetBasePackage
				+ "]";
	}

}
