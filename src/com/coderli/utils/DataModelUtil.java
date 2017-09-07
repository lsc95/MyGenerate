package com.coderli.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.coderli.entity.Column;
import com.coderli.entity.Table;
import com.coderli.exception.ConvertException;

public class DataModelUtil {
	public static List<String> getTables(Map<String, Table> source) {
		List<String> tableNames = new ArrayList<>();
		Iterator<Entry<String, Table>> iterator = source.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Table> entry = iterator.next();
			String tableName = entry.getKey();
			tableNames.add(tableName);
		}
		return tableNames;
	}

	public static List<Column> getColumns(Map<String, Table> map,String tableName) throws ConvertException {
		Table table = map.get(tableName);
		List<Column> data = new ArrayList<>();
		Map<String, Column> columns = table.getColumns();
		Iterator<Entry<String, Column>> iterator = columns.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String,Column> entry = (Map.Entry<String,Column>) iterator.next();
			Column column = entry.getValue();
			column.setType(ConvertType.convertToJava(column.getType()));
			data.add(column);
		}
		return data;
	}
	/**
	 * 获取表中的主键（暂时只支持单个主键的操作）
	 * @param map数据库中的表数据
	 * @param tableName表明
	 * @return 返回主键字段（暂时只支持单主键）
	 * @throws ConvertException
	 */
	public static Column getPrimaryKey(Map<String, Table> map,String tableName) throws ConvertException{
		Table table = map.get(tableName);
		List<Column> keys = table.getPrimaryKeys();
		if(keys==null||keys.size()<=0){
			return null;
		}
		for (Column column : keys) {
			String name = column.getName();
			Column col = table.getColumns().get(name);
			column.setType(col.getType());
		}
		return keys.get(0);
	}
}
