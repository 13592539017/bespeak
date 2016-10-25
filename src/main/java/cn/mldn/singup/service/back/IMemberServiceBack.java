package cn.mldn.singup.service.back;

import java.util.List;
import java.util.Map;

import cn.mldn.singup.vo.Member;

public interface IMemberServiceBack {
	/**
	 * 实现全部用户的列表显示，调用IMemberDAO.findAll()查询
	 * @return 如果没有数据则集合长度为0（size() == 0） 
	 */
	public List<Member> list() ;
	/**
	 * 实现密码的变更处理
	 * @param mid 要修改密码的当前用户
	 * @param oldPassword 原始密码（加密后的数据）
	 * @param newPassword 新的密码（加密后的数据）
	 * @return 修改成功返回true，否则返回false
	 */
	public boolean editPassword(String mid,String oldPassword,String newPassword) ;
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
