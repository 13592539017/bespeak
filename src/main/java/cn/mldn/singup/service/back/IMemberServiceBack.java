package cn.mldn.singup.service.back;

import cn.mldn.singup.vo.Member;

public interface IMemberServiceBack {
	/**
	 * 根据用户的id查询出用户的完整数据，调用：IMemberDAO.findById()
	 * @param mid 
	 * @return
	 */
	public Member get(String mid) ;
}
