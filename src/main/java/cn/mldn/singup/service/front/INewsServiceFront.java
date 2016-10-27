package cn.mldn.singup.service.front;

import java.util.Map;

import cn.mldn.singup.vo.News;

public interface INewsServiceFront {
	
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
	 * 进行信息的查询处理
	 * @param nid
	 * @return
	 */
	public News show(int nid) ;
}
