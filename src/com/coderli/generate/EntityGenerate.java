package com.coderli.generate;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.coderli.entity.Column;
import com.coderli.entity.ConfigInfo;
import com.coderli.entity.Table;
import com.coderli.utils.ConvertType;
import com.coderli.utils.DButils;
import com.coderli.utils.DataModelUtil;
import com.coderli.utils.GenerateUtil;
import com.coderli.utils.ParseProperties;
import com.coderli.utils.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
@SuppressWarnings("all")
public class EntityGenerate {
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
		String ftlName="entity.ftl";
		//遍历数据库数据生成对应的java类
		for (String tableName : tableNames) {
			Map dataModel = new HashMap<>();
			//表明转换为类名
			String className=StringUtil.toClassName(tableName);
			dataModel.put("className", className);
			dataModel.put("baseProjectName", info.getTargetBasePackage());
			List<Column> columns = DataModelUtil.getColumns(map,tableName);
			dataModel.put("columns", columns);
			String generateFileName=className+".java";
			GenerateUtil.generate(info.getGeneratePath(),ftlDirPath, ftlName, dataModel, generateFileName);
		}	
	}
	public static void main(String[] args) throws Exception {
		new EntityGenerate().generateEntity();
	}
}
