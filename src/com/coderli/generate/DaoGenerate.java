package com.coderli.generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import com.coderli.entity.ConfigInfo;
import com.coderli.entity.Table;
import com.coderli.utils.DButils;
import com.coderli.utils.DataModelUtil;
import com.coderli.utils.ParseProperties;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class DaoGenerate {
	public File generateDao(String fileName,String path) throws IOException {
		
		ConfigInfo info = ParseProperties.parseGenerateProperties(fileName);
		
		File f = new File(path);
		OutputStreamWriter fos = null;
		try {
			Configuration conf = new Configuration(Configuration.VERSION_2_3_26);
			conf.setDefaultEncoding("utf-8");
			// 定义FM的目录
			File ftlDir = new File(this.getClass().getClassLoader()
					.getResource("/").getPath()
					+ "/ftl");
			// 设置模板目录
			conf.setDirectoryForTemplateLoading(ftlDir);
			// 加载poem.ftl模板
			Template daoTemplate = conf.getTemplate("dao.ftl");
			Template daoImplTemplate = conf.getTemplate("daoImpl.ftl");
			Template entityTemplate = conf.getTemplate("entity.ftl");
			Template mybatisTemplate = conf.getTemplate("mybatis.ftl");
			Template serviceTemplate = conf.getTemplate("service.ftl");
			Template serviceImplTemplate = conf.getTemplate("serviceImpl.ftl");
			// todo....从数据库获取
		//	Table table = DButils.getTableInfo(info);
			// Map 作为DataModel   数据封装
			//Map dataModel = DataModelUtil.putData();
			// 输出到字符文件中
			//fos = new FileWriter(f);
			// 将dataModel中的数据吸入到poem.ftl模板中,保存到c:/300/poems.txt文件下
			//t.process(dataModel, fos);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(fos!=null)
				fos.close();
		}
		return f;

	}
}
