<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${fullClassName}">

	<insert id="insert" parameterType="${fullClassName}">
		INSERT INTO t_brand (<#list columns as column><#if (column!=primarykey)>${column}</#id></#list>)
		VALUES (<#list columns as column><#if (column!=primarykey)>#{${column}},</#id></#list>)
	</insert>
	
	<update id="update" parameterType="${fullClassName}">
		UPDATE t_brand
			SET  
				<#list columns as column>${column}=#{${column}}</#list>
			WHERE ${primarykey} = #{${primarykey}}
			
	</update>
	
	<delete id="delete" parameterType="Integer">
		DELETE FROM ${tableName} where ${primarykey} = #{${primarykey}}
	</delete>
	
	<select id="findById" parameterType="Integer" resultType="${fullClassName}">
		select
				t.brand_id brandId ,
				t.brand_name brandName 
		from t_brand t where t.brand_id = #{value}
	</select>
	
	<select id="findByProperty" parameterType="java.util.Map"   resultType="java.util.LinkedHashMap">
		select 
				t.brand_id brandId ,
				t.brand_name brandName 
		 from t_brand t where 1=1 
		<if test="brandId != null">
		and t.brand_id =#{brandId}
		</if>
		<if test="brandName != null">
		and t.brand_name =#{brandName}
		</if>
		<if test="st != null and r != null">
			limit #{st} , #{r}
		</if>
	</select>

	<select id="countByProperty" parameterType="java.util.Map"   resultType="Long">
		select count(*) cnt from t_brand t where 1=1 
		<if test="brandId != null">
		and t.brand_id =#{brandId}
		</if>
		<if test="brandName != null">
		and t.brand_name =#{brandName}
		</if>
	</select>

</mapper>