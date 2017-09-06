package com.coderli.utils;

import com.coderli.exception.ConvertException;

/**
 * 用于数据库和java类型转换
 * @author coderli
 *
 */
public class ConvertType {
	public static String convertToJava(String type) throws ConvertException{
		if("INT".equalsIgnoreCase(type)||"TINYINT".equalsIgnoreCase(type)||"SMALLINT".equalsIgnoreCase(type)||"MEDIUMINT".equalsIgnoreCase(type)){
			return "Integer";
		}else if("VARCHAR".equalsIgnoreCase(type)||"CHAR".equalsIgnoreCase(type)||"TEXT".equalsIgnoreCase(type)){
			return "String";
		}else if("BIT".equalsIgnoreCase(type)){
			return "Boolean";
		}else if("BIGINT".equalsIgnoreCase(type)){
			return "BigInteger";
		}else if("FLOAT".equalsIgnoreCase(type)){
			return "Float";
		}else if("DOUBLE".equalsIgnoreCase(type)){
			return "Double";
		}else if("DECIMAL".equalsIgnoreCase(type)){
			return "BigDecimal";
		}else if("BOOLEAN".equalsIgnoreCase(type)){
			return "Integer";
		}else if("BOOLEAN".equalsIgnoreCase(type)){
			return "Integer";
		}else if("DATE".equalsIgnoreCase(type)){
			return "Date";
		}else if("TIME".equalsIgnoreCase(type)){
			return "Time";
		}else if("DATETIME".equalsIgnoreCase(type)||"TIMESTAMP".equalsIgnoreCase(type)){
			return "Timestamp";
		}else if("BLOB".equalsIgnoreCase(type)){
			return "Byte[]";
		}else {
			throw new ConvertException("找不到对应的数据类型！");
		}
	}
}
