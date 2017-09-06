package ${targetPackage}.dao;

import java.util.List;
import java.util.Map;

public class ${className?cap_first+"DAO"}{
	public Map findByProperties(Map map);
	public List<Map> findAll();
	public void update(${className?cap_first} ${className});
	public void delete(${className?cap_first} ${className});
	public void insert(${className?cap_first} ${className});
}