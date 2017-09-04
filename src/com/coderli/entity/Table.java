package com.coderli.entity;

import java.util.List;
import java.util.Map;

/**
 * 数据库中的表
 * 
 * @author coderli
 * 
 */
public class Table {
	// 表名字
	private String name;
	// 表中的属性
	private Map<String, Column> columns;
	// 表中的主键
	private List<Column> primaryKeys;

	public Table() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Column> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, Column> columns) {
		this.columns = columns;
	}

	public List<Column> getPrimaryKeys() {
		return primaryKeys;
	}

	public void setPrimaryKeys(List<Column> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}

}
