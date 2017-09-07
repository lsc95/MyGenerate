package ${basePackage}.dao;

import java.util.List;
import java.util.Map;
import ${basePackage}.bean.${className};

public interface ${className?cap_first+"DAO"}{
	public Long countByProperty(Map parameters);
	public List<Map> findByProperty(Map parameters);
	public List<${className}> findById(${className} ${className?lower_case})
	public void delete(${primary.type} ${primary.name});
	public void update(${className} ${className});
	public void insert(${className} ${className});
}