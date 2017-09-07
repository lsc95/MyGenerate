package ${basePackage}.service;

import java.util.*;
import ${basePackage}.bean.${beanName}

public interface ${className}{
	
	public void create(${beanName} entity);
	
	public void update(${beanName} entity);
	
	public void delete(${primary.type} ${primary.name});
	
	public ${beanName} findById(${primary.type} ${primary.name});
	
	public List<Map> findByProperty(Map params);
	
	public Long countByProperty(Map params);
}
