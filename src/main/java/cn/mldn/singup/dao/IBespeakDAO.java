package cn.mldn.singup.dao;

import java.util.List;
import java.util.Map;

import cn.mldn.singup.vo.Bespeak;

public interface IBespeakDAO {
	public boolean doCreate(Bespeak vo) ;
	/**
	 * 实现分页的数据列表，处理的时候采用动态SQL，如果没有column或keyWord表示查询全部
	 * @param params 包含有如下的几个参数：<br>
	 * 1、key = column、value = 模糊查询的数据列；<br>
	 * 2、key = keyWord、value = 模糊查询关键字；<br>
	 * 3、key = start、value = (currentPage - 1) * lineSize，开始记录数；<br>
	 * 4、key = lineSize、value = 每页显示的数据行数。 <br>
	 * 5、key = status、value = 处理状态。<br>
	 * @return
	 */	
	public List<Bespeak> findAllSplitByStatus(Map<String,Object> params) ;
	/**
	 * 进行数据的模糊查询统计操作，处理采用了的动态SQL，没有column与keyWord统计全表数据量
	 * @param params  包含有如下的几个参数：<br>
	 * 1、key = column、value = 模糊查询的数据列；<br>
	 * 2、key = keyWord、value = 模糊查询关键字；<br>
	 * 3、key = status、value = 处理状态。<br>
	 * @return 
	 */
	public Integer getAllCountByStatus(Map<String,Object> params) ; 
	
}
