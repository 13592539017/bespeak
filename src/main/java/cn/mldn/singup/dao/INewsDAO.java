package cn.mldn.singup.dao;

import cn.mldn.singup.vo.News;

public interface INewsDAO {
	/**
	 * 根据新闻标题查询新闻数据以确定该数据是否存在
	 * @param title
	 * @return
	 */
	public News findByTitle(String title) ;
	/**
	 * 进行数据的追加操作
	 * @param vo
	 * @return
	 */
	public boolean doCreate(News vo) ;
}
