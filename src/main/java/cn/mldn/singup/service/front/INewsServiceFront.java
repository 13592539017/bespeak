package cn.mldn.singup.service.front;

import cn.mldn.singup.vo.News;

public interface INewsServiceFront {
	/**
	 * 进行信息的查询处理
	 * @param nid
	 * @return
	 */
	public News show(int nid) ;
}
