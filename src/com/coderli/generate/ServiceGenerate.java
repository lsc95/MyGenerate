package com.coderli.generate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coderli.entity.Column;
import com.coderli.entity.ConfigInfo;
import com.coderli.entity.Table;
import com.coderli.utils.DButils;
import com.coderli.utils.DataModelUtil;
import com.coderli.utils.GenerateUtil;
import com.coderli.utils.ParseProperties;
import com.coderli.utils.StringUtil;
@SuppressWarnings("all")
public class ServiceGenerate {
	public void generateEntity() throws Exception{
		//加载配置文件
		ConfigInfo info = ParseProperties.parseGenerateProperties("generate.properties");
		//数据库中的所有表数据
		Map<String, Table> map = DButils.getDataBaseInfo(info);
		//获取数据库中的表名
		List<String> tableNames = DataModelUtil.getTables(map);
		//获取ftl文件的目录路径
		String ftlDirPath=this.getClass().getClassLoader().getResource("").getPath()+"/ftl";
		//使用的ftl文件名
		String ftlName="service.ftl";
		//遍历数据库数据生成对应的java类
		for (String tableName : tableNames) {
			Map dataModel = new HashMap<>();
			//表明转换为类名
			String beanName=StringUtil.toClassName(tableName);
			dataModel.put("className", beanName+"Service");
			dataModel.put("beanName", beanName);
			dataModel.put("basePackage", info.getTargetBasePackage());
			Column primary = DataModelUtil.getPrimaryKey(map, tableName);
			dataModel.put("primary", primary);
			String generateFileName=beanName+"Service.java";
			GenerateUtil.generate(info.getGeneratePath(),ftlDirPath, ftlName, dataModel, generateFileName);
		}	
	}
	public static void main(String[] args) throws Exception {
		new ServiceGenerate().generateEntity();
	}
}
