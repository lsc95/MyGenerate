package com.coderli.generate;

import java.io.IOException;
import java.util.Properties;

public class Generate {
	public static void main(String[] args) throws IOException {
		Properties pro = new Properties();
		pro.load(Generate.class.getClassLoader().getResourceAsStream("generate.properties"));
		String driver = pro.getProperty("jdbc.mysql.driver");
		String url = pro.getProperty("jdbc.mysql.url");
		String name = pro.getProperty("jdbc.mysql.username");
		String password = pro.getProperty("jdbc.mysql.password");
		String generatePath = pro.getProperty("generate.generatePath");
		
	}
}
