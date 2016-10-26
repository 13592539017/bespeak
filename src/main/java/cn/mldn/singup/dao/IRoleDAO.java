package cn.mldn.singup.dao;

import java.util.List;
import java.util.Set;

import cn.mldn.singup.vo.Role;

public interface IRoleDAO {
	/**
	 * 实现所有角色信息的查询显示
	 * @return
	 */
	public List<Role> findAll() ;
	
	public Set<String> findAllRoleFlag(String mid) ; 
}
