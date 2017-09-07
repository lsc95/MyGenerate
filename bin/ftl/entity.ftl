package ${baseProjectName}.bean;
import java.sql.Date;
import java.math.BigInteger;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.io.Serializable;
public class ${className?cap_first} implements  Serializable{
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
