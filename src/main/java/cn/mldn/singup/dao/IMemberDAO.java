package cn.mldn.singup.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.singup.vo.Member;

public interface IMemberDAO {
	/**
	 * 实现用户密码的变更，但是不会变更超级管理员的密码
	 * @param params 要进行变更的数据，包含有以下内容：<br>
	 * key = mid、value = 要修改的用户编号；<br>
	 * key = password、value = 要更改的新密码（已经加密过的）；<br>
	 * @return
	 */
	public boolean doUpdatePasswordByAdmin(Map<String,Object> params) ;   
	/**
	 * 查询全部的用户数据 
	 * @return
	 */
	public List<Member> findAll() ;
	/** 
	 * 根据id实现member数据的查询过程
	 * @param mid 要查询的mid的数据
	 * @return
	 */
	public Member findById(String mid) ;
	/**
	 * 进行用户密码的修改处理
	 * @param params 此时要传递两个参数：<br>
	 * 参数一：key = mid、value = 要修改的用户编号；<br>
	 * 参数二：key = newPassword、value = 加密后的新密码；<br>
	 * @return 修改成功返回true，否则返回false
	 */ 
	public boolean doUpdatePassword(Map<String,Object> params) ;
}
