package com.coderli.utils;

import java.io.IOException;
import java.util.Properties;

import com.coderli.entity.ConfigInfo;

public class ParseProperties {
	
	public static ConfigInfo parseGenerateProperties(String fileName) throws IOException {
		Properties properties = new Properties();
		properties.load(ParseProperties.class.getClassLoader().getResourceAsStream(fileName));
		ConfigInfo info = new ConfigInfo();
		info.setDriver(properties.getProperty("jdbc.mysql.driver"));
		info.setGeneratePath(properties.getProperty("generate.generatePath"));
		info.setName(properties.getProperty("jdbc.mysql.username"));
		info.setPassword(properties.getProperty("jdbc.mysql.password"));
		info.setUrl(properties.getProperty("jdbc.mysql.url"));
		info.setTargetBasePackage(properties.getProperty("generate.projectName"));
		return info;
	}

}
