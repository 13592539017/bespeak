package cn.mldn.singup.service.front.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.singup.dao.INewsDAO;
import cn.mldn.singup.service.front.INewsServiceFront;
import cn.mldn.singup.service.front.abs.AbstractServiceFront;
import cn.mldn.singup.vo.News;
@Service
public class NewsServiceFrontImpl extends AbstractServiceFront implements INewsServiceFront {
	@Resource
	private INewsDAO newsDAO ;
	@Override
	public News show(int nid) {
		News vo = this.newsDAO.findById(nid) ;
		if (vo.getFlag().equals(1)) {	// 只查询发布的公告信息
			return vo ;
		} 
		return null;
	}

}
