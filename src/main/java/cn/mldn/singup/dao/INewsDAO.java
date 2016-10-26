package cn.mldn.singup.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.singup.vo.News;

public interface INewsDAO {
	/**
	 * 公告信息的更新处理
	 * @param vo
	 * @return
	 */
	public boolean doUpdate(News vo) ;
	
	/**
	 * 根据编号查询一个公告的完整信息
	 * @param nid 公告编号
	 * @return
	 */
	public News findById(Integer nid) ;
	
	/**
	 * 实现分页的数据列表，处理的时候采用动态SQL，如果没有column或keyWord表示查询全部
	 * @param params 包含有如下的几个参数：<br>
	 * 1、key = column、value = 模糊查询的数据列；<br>
	 * 2、key = keyWord、value = 模糊查询关键字；<br>
	 * 3、key = start、value = (currentPage - 1) * lineSize，开始记录数；<br>
	 * 4、key = lineSize、value = 每页显示的数据行数。 <br>
	 * 5、key = flag、value = 发布状态。<br>
	 * @return
	 */	
	public List<News> findAllSplitByFlag(Map<String,Object> params) ;
	/**
	 * 进行数据的模糊查询统计操作，处理采用了的动态SQL，没有column与keyWord统计全表数据量
	 * @param params  包含有如下的几个参数：<br>
	 * 1、key = column、value = 模糊查询的数据列；<br>
	 * 2、key = keyWord、value = 模糊查询关键字；<br>
	 * 5、key = flag、value = 发布状态。<br>
	 * @return 
	 */
	public Integer getAllCountByFlag(Map<String,Object> params) ;
	
	/**
	 * 实现分页的数据列表，处理的时候采用动态SQL，如果没有column或keyWord表示查询全部
	 * @param params 包含有如下的几个参数：<br>
	 * 1、key = column、value = 模糊查询的数据列；<br>
	 * 2、key = keyWord、value = 模糊查询关键字；<br>
	 * 3、key = start、value = (currentPage - 1) * lineSize，开始记录数；<br>
	 * 4、key = lineSize、value = 每页显示的数据行数。 <br>
	 * @return
	 */
	public List<News> findAllSplit(Map<String,Object> params) ;
	/**
	 * 进行数据的模糊查询统计操作，处理采用了的动态SQL，没有column与keyWord统计全表数据量
	 * @param params  包含有如下的几个参数：<br>
	 * 1、key = column、value = 模糊查询的数据列；<br>
	 * 2、key = keyWord、value = 模糊查询关键字；<br>
	 * @return 
	 */
	public Integer getAllCount(Map<String,Object> params) ;
	
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
