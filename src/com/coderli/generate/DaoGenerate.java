package com.coderli.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import com.coderli.utils.ParseProperties;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class DaoGenerate {
	public File generateDao() throws Exception {
		// 获取配置文件信息
		ConfigInfo info = ParseProperties
				.parseGenerateProperties("generate.properties");
		String base = "D:/fileTest";
		Map<String, Table> map = DButils.getDataBaseInfo(info);
		Iterator<Entry<String, Table>> iterator = map.entrySet().iterator();
		// 使用FreeMarker
		Configuration configuration = new Configuration(
				Configuration.VERSION_2_3_26);
		File ftlDir = new File(this.getClass().getClassLoader().getResource("")
				.getPath()
				+ "/ftl");
		configuration.setDirectoryForTemplateLoading(ftlDir);
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("dao.ftl");
		while (iterator.hasNext()) {
			Map.Entry<String, Table> entry = iterator.next();
			Table value = entry.getValue();
			Map dataModel = new HashMap();
			dataModel.put("targetPackage", info.getTargetPackage());
			dataModel.put("className", value.getName());
			FileWriter fw = new FileWriter(new File(base + "/"
					+ value.getName() + "DAO.java"));
			template.process(dataModel, fw);
			fw.close();
		}
		System.out.println("generate success！");
		return null;
	}
	public static void main(String[] args) throws Exception {
		new DaoGenerate().generateDao();
	}
}
