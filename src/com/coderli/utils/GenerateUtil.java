package com.coderli.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import com.coderli.entity.ConfigInfo;
import freemarker.template.Configuration;
import freemarker.template.Template;

@SuppressWarnings("all")
public class GenerateUtil {
	/**
	 * 根据数据生成指定的单个文件
	 * @param ftlDirPath模板文件的基本文件路径
	 * @param ftlName模板文件名
	 * @param dataModel数据
	 * @param generateFileName生成的文件名
	 * @throws Exception抛出的异常
	 */
	public static void generate(String base,String ftlDirPath,String ftlName,Map dataModel,String generateFileName) throws Exception {
		// 使用FreeMarker
		Configuration configuration = new Configuration(Configuration.VERSION_2_3_26);
		File ftlDir = new File(ftlDirPath);
		configuration.setDirectoryForTemplateLoading(ftlDir);
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate(ftlName);
		FileWriter fw = new FileWriter(new File(base + "/"+ generateFileName));
		template.process(dataModel, fw);
		fw.close();
		System.out.println("generate success！");
	}
}
