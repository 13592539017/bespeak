package cn.mldn.singup.service.back;

import java.util.Map;

import cn.mldn.singup.vo.Member;

public interface IMemberServiceBack {
	/**
	 * 根据用户的id查询出用户的完整数据，调用：IMemberDAO.findById()
	 * @param mid 
	 * @return
	 */
	public Member get(String mid) ;
	/**
	 * 是进行用户对应的角色以及所有权限数据的查询操作，要调用如下的接口方法：<br>
	 * 1、查询所有的角色：IRoleDAO.findAllRoleFlag()；<br>
	 * 2、查询所有的权限：IActionDAO.findAllActionFlag()；<br>
	 * @param mid
	 * @return 返回的结果包含有如下的几个内容：<br>
	 * 1、key = allRoles = value = IRoleDAO.findAllRoleFlag()，Set集合；<br>
	 * 2、key = allActions = value = IActionDAO.findAllActionFlag()，Set集合；<br>
	 */
	public Map<String,Object> listAuthByMember(String mid) ;
}
