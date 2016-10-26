package cn.mldn.singup.service.back;

import java.util.Map;

import cn.mldn.singup.vo.News;

public interface INewsServiceBack {
	/**
	 * 实现数据的分页查询操作，需要进行如下的查询：<br>
	 * 1、调用INewsDAO.findAllSplit()方法查询出具体的数据内容；<br>
	 * 2、调用INewsDAO.getAllCount()方法统计数据量。<br>
	 * @param column 模糊查询列
	 * @param keyWord 模糊查询关键字
	 * @param currentPage 当前所在页
	 * @param lineSize 每页显示的数据行
	 * @return 返回的结果包含有如下数据：<br>
	 * 1、key = allNews、value = INewsDAO.findAllSplit()；<br>
	 * 2、key = newsCount、value = INewsDAO.getAllCount()；<br>
	 */
	public Map<String, Object> listNone(String column, String keyWord, int currentPage, int lineSize);
		 

	/**
	 * 实现数据的分页查询操作，需要进行如下的查询：<br>
	 * 1、调用INewsDAO.findAllSplit()方法查询出具体的数据内容；<br>
	 * 2、调用INewsDAO.getAllCount()方法统计数据量。<br>
	 * @param column 模糊查询列
	 * @param keyWord 模糊查询关键字
	 * @param currentPage 当前所在页
	 * @param lineSize 每页显示的数据行
	 * @return 返回的结果包含有如下数据：<br>
	 * 1、key = allNews、value = INewsDAO.findAllSplit()；<br>
	 * 2、key = newsCount、value = INewsDAO.getAllCount()；<br>
	 */
	public Map<String, Object> list(String column, String keyWord, int currentPage, int lineSize);
	
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
