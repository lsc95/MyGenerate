<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
	<!-- 配置运行环境，可以配置多个，可以开发用mysql,上线用oracle等，只需配置一个mysq和一个oracle的配置 -->
	<environments default="mysqlEM">
		<!--配置文件的id，用于environments选择运行环境，这里是mysql的运行环境-->
		<environment id="mysqlEM">
			<!-- 此处type有两个选项，JDBC和MANAGER,jdbc默认使用jdbc的事务管理，manager使用第三方管理事务 -->
			<transactionManager type="JDBC" />
			<dataSource type="POOLED">
				<!-- mysql的驱动 -->
				<property name="driver" value="${jdbc.driver}"/>
				<!--mysql jdbc的uri-->
				<property name="url" value="${jdbc.url}"/>
				<!-- mysql数据库的用户名-->
				<property name="username" value="${jdbc.username}"/>
				<!-- mysql数据库的密码-->
				<property name="password" value="${jdbc.password}"/>
			</dataSource>
		</environment>
	</environments>
	<!-- 注册映射文件 -->
	<mappers>
		<#list mappers as mapper>
		<mapper resource="${mapper}"/>
		</#list>
	</mappers>
</configuration>