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
实体类模板(Entity.ftl)
```
package ${targetPackage}.bean;
import java.sql.Date;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
public class ${className?cap_first}{
	//属性
	<#list columns as column>
	private ${column.type} ${column.name};
	</#list>
	//空参构造
	public ${className}(){
	
	}
	//set和get方法的生成
	<#list columns as column>
	public void set${column.name?cap_first}(${column.type} ${column.name}){
		this.${column.name}=${column.name};
	}
	public ${column.type} get${column.name?cap_first}(){
		return ${column.name};
	}
	</#list>
}
```
实体类生成的核心代码(先实现功能，后面整理优化代码)
```
public File generateEntity() throws Exception{
	//获取配置文件信息
	ConfigInfo info = ParseProperties.parseGenerateProperties("generate.properties");
	String base = "D:/fileTest";	
	Map<String, Table> map = DButils.getDataBaseInfo(info);
	Iterator<Entry<String, Table>> iterator = map.entrySet().iterator();
	//使用FreeMarker
	Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
	File ftlDir = new File(this.getClass().getClassLoader().getResource("").getPath()+"/ftl");
	configuration.setDirectoryForTemplateLoading(ftlDir);
	configuration.setDefaultEncoding("utf-8");
	Template template = configuration.getTemplate("entity.ftl");
	while (iterator.hasNext()) {
		Map.Entry<String,Table> entry = iterator.next();
		Table value = entry.getValue();
		Map dataModel = new HashMap();
		dataModel.put("targetPackage", info.getTargetPackage());
		dataModel.put("className", value.getName());
		List columns = new ArrayList<>();
		Iterator<Entry<String, Column>> cols = value.getColumns().entrySet().iterator();
		while (cols.hasNext()) {
			Map.Entry<String,Column> col =cols.next();
			Column column = col.getValue();
			column.setType(ConvertType.convertToJava(column.getType()));
			columns.add(column);
		}
		dataModel.put("columns", columns);
		FileWriter fw = new FileWriter(new File(base+"/"+value.getName()+".java"));
		template.process(dataModel, fw);
		fw.close();
	}
	System.out.println("generate success！");
	return null;
}
```
DAO层(接口)生成完成，大部分代码复制于Entity。等待后期改进