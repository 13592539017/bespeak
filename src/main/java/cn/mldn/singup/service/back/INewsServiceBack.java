package cn.mldn.singup.service.back;

import java.util.Map;

import cn.mldn.singup.vo.News;

public interface INewsServiceBack {
	/**
	 * 实现新闻数据的保存处理，调用INewsDAO.doCreate()方法
	 * @param vo
	 * @return
	 */
	public boolean add(News vo) ;
	/**
	 * 在进行新闻增加前数据查询处理，要查询如下内容：<br>
	 * 1、要知道所有的新闻数据分类，查询：IDictionaryDAO.findAllByItem()方法；<br>
	 * @return 返回的内容包含有如下数据：<br>
	 * 1、key = allNewsType、value = IDictionaryDAO.findAllByItem("news")
	 */
	public Map<String,Object> addPre() ;
	/**
	 * 主要是为了Ajax验证使用
	 * @param title 进行标题的验证处理
	 * @return
	 */
	public News getByTitle(String title) ;
}
