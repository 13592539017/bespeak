package cn.mldn.singup.dao;

import cn.mldn.singup.vo.Member;

public interface IMemberDAO {
	/** 
	 * 根据id实现member数据的查询过程
	 * @param mid 要查询的mid的数据
	 * @return
	 */
	public Member findById(String mid) ;
}
