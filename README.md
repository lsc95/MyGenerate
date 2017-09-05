---
mybatis代码生成器
---
### 项目介绍
使用freemarker模板引擎开发mybatis代码生成器
### 环境介绍
开发工具：MyEclipse2014<br />
jdk：1.7<br />
freemarker: 2.3.26<br />
### 获取数据库中所有表的信息(核心代码如下)
```
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
```

