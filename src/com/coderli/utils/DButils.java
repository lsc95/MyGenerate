package com.coderli.utils;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import com.coderli.entity.Column;
import com.coderli.entity.ConfigInfo;
import com.coderli.entity.Table;
import com.mysql.jdbc.Connection;

public class DButils {
	
	
	public static Map<String, Table> getDataBaseInfo(ConfigInfo info)throws Exception {
		Connection conn=getConn(info);
		DatabaseMetaData metaData = conn.getMetaData();
		Map<String, Table> map = getTableInfo(metaData);
		return map;
	}
	
	public static Connection getConn(ConfigInfo info) throws ClassNotFoundException, SQLException{
		Class.forName(info.getDriver());
		return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8", "root", "");
	}

	public static Map<String, Table> getTableInfo(DatabaseMetaData metaData)throws SQLException {
		//创建容器保存数据库表的信息
		Map<String, Table> map = new LinkedHashMap<>();
		//查询数据库中的所有表
		ResultSet tables = metaData.getTables(null, "%", "%",new String[] { "TABLE" });
		while (tables.next()) {
			//创建表对象
			Table table = new Table();
			table.setName(tables.getString("TABLE_NAME"));
			//创建表中字段的属性
			Map<String, Column> column = new HashMap<>();
			ResultSet cols = metaData.getColumns(null, "%", table.getName(), "%");
			while (cols.next()) {
				Column col = new Column();
				col.setName(cols.getString("COLUMN_NAME"));
				col.setType(cols.getString("TYPE_NAME"));
				column.put(col.getName(), col);
			}
			table.setColumns(column);
			List<Column> keyList = new ArrayList<>();
			table.setPrimaryKeys(keyList);
			// 获取主键信息
			ResultSet keys = metaData.getPrimaryKeys(null, "%", table.getName());
			while (keys.next()) {
				Column key = new Column();
				key.setName(keys.getString("COLUMN_NAME"));
				key.setKey(true);
				keyList.add(key);
			}
			map.put(table.getName(), table);
		}
		return map;
	}
	//测试获取表信息
	public static void main(String[] args) throws Exception {
		ConfigInfo info = ParseProperties.parseGenerateProperties("generate.properties");
		Map<String, Table> map = DButils.getDataBaseInfo(info);
		Set<Entry<String,Table>> entrySet = map.entrySet();
		Iterator<Entry<String, Table>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, Table> next = iterator.next();
			System.out.println(next.getKey());
			System.out.println("\t"+next.getValue().getColumns());
		}
	}
}
