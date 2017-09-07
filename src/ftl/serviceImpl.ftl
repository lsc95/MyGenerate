package ${basePackage}.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import ${basePackage}.bean.${beanName};
import ${basePackage}.service.${serviceName};
import ${basePackage}.dao.${daoName};

@Service("${className?uncap_first}")
@Transactional(rollbackFor=Exception.class)
public class ${className} implements ${serviceName}{
	@Resource(name="${daoName?uncap_first}")
	private ${daoName} ${daoName?lower_case} = null;
	
	public void create(${beanName} entity){
		${daoName?lower_case}.insert(entity);
	}
	
	public void update(${beanName} entity){
		${daoName?lower_case}.update(entity);
	}
	
	public void delete(${primary.type} ${primary.name}){
		${daoName?lower_case}.delete(${primary.name});
	}
	
	public ${beanName} findById(${primary.type} ${primary.name}){
		return ${daoName?lower_case}.findById(${primary.name});
	}
	
	public List<Map> findByProperty(Map params){
		return ${daoName?lower_case}.findByProperty(params);
	}
	
	public Long countByProperty(Map params){
		return ${daoName?lower_case}.countByProperty(params);
	}
}
