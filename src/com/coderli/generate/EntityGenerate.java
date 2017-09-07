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

import freemarker.template.Configuration;
import freemarker.template.Template;
@SuppressWarnings("all")
public class EntityGenerate {
	public void generateEntity() throws Exception{
		ConfigInfo info = ParseProperties.parseGenerateProperties("generate.properties");
		//数据库中的所有表数据
		Map<String, Table> map = DButils.getDataBaseInfo(info);
		//获取数据库中的表明
		List<String> tableNames = DataModelUtil.getTables(map);
		String ftlDirPath=this.getClass().getClassLoader().getResource("").getPath()+"/ftl";
		String ftlName="entity.ftl";
		for (String tableName : tableNames) {
			String generateFileName=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
			Map dataModel = new HashMap<>();
			//baseProjectName  className columns(name,type)
			dataModel.put("baseProjectName", info.getTargetBasePackage());
			dataModel.put("className", generateFileName);
			List<Column> columns = DataModelUtil.getColumns(map,tableName);
			dataModel.put("columns", columns);
			GenerateUtil.generate(info.getGeneratePath(),ftlDirPath, ftlName, dataModel, generateFileName);
		}	
	}
	public static void main(String[] args) throws Exception {
		new EntityGenerate().generateEntity();
	}
}
