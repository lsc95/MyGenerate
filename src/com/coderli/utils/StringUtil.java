package com.coderli.utils;

public class StringUtil {
	/**
	 * 将数据库中的表名转换为符合命名规范的类名
	 * @param className输入的数据库表明
	 * @return 首字母大写的类名
	 */
	public static String toClassName(String className){
		return className.substring(0,1).toUpperCase()+className.substring(1);
	}
}
