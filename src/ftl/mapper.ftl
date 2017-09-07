<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${basePackage}.dao.${daoName}">

	<insert id="insert" parameterType="${basePackage}.bean.${className}">
		INSERT INTO ${tableName} (<#list columns as column><#if primary.name != column.name>${column.name}<#if column_has_next>,</#if></#if></#list>)
		VALUES (<#list columns as column><#if primary.name != column.name>${"#{"+column.name+"}"}<#if column_has_next>,</#if></#if></#list>)
	</insert>
	
	<update id="update" parameterType="${basePackage}.bean.${className}">
		UPDATE ${tableName}
			SET  
				<#list columns as column>
				<#if column_index !=0  >,</#if>${column.name} = ${"#{"+column.name+"}"}
				</#list>
			WHERE ${primary.name} = ${"#{"+primary.name+"}"}
			
	</update>
	
	<delete id="delete" parameterType="${primary.type}">
		DELETE FROM ${tableName} where ${primary.name} = ${"#{"+primary.name+"}"}
	</delete>
	
	<select id="findById" parameterType="${primary.type}" resultType="${basePackage}.bean.${className}">
		select
			<#list columns as column>
				t.${column.name} <#if column_has_next>,</#if>
			</#list>
		from ${tableName} t where t.${primary.name} = ${"#{"+primary.name+"}"}
	</select>
	
	<select id="findByProperty" parameterType="java.util.Map"  resultType="java.util.LinkedHashMap">
		select 
			<#list columns as column>
				t.${column.name} <#if column_has_next>,</#if>
			</#list>
		 from ${tableName} t where 1=1 
		<#list columns as column>
		<if test="${column.name} != null">
		and t.${column.name} =${"#{"+column.name+"}"}
		</if>
		</#list>
		<if test="st != null and r != null">
			limit ${'#{'}st} , ${'#{'}r}
		</if>
	</select>
	<select id="countByProperty" parameterType="java.util.Map"   resultType="Long">
		select count(*) cnt from ${tableName} t where 1=1 
		<#list columns as column>
		<if test="${column.name} != null">
		and t.${column.name} =${"#{"+column.name+"}"}
		</if>
		</#list>
	</select>
</mapper>