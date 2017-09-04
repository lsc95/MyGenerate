package com.coderli.entity;

/**
 * 表中的字段
 * 
 * @author coderli
 * 
 */
public class Column {
	//字段名
	private String name;
	//字段类型
	private String type;
	//是否为主键
	private boolean isKey;

	public Column() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isKey() {
		return isKey;
	}

	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

}
