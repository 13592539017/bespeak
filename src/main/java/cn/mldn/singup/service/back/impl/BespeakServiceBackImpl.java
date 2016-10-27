package cn.mldn.singup.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Service;

import cn.mldn.singup.dao.IBespeakDAO;
import cn.mldn.singup.service.back.IBespeakServiceBack;
import cn.mldn.singup.service.back.abs.AbstractServiceBack;
import cn.mldn.singup.vo.Bespeak;
@Service
public class BespeakServiceBackImpl extends AbstractServiceBack implements IBespeakServiceBack {
	@Resource
	private IBespeakDAO bespeakDAO ;
	
	@Override
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:list")
	public Bespeak get(int beid) {
		return this.bespeakDAO.findById(beid);
	} 
	
	@Override
	@RequiresRoles("bespeak")
	@RequiresPermissions("bespeak:list")
	public Map<String, Object> listByStatus(String column, String keyWord, int currentPage, int lineSize,
			Integer status) {
		Map<String,Object> map = super.handleParams(column, keyWord, currentPage, lineSize) ;
		map.put("status", status) ;
		Map<String,Object> result = new HashMap<String,Object>() ;
		result.put("allBespeaks", this.bespeakDAO.findAllSplitByStatus(map)) ;
		result.put("bespeakCount", this.bespeakDAO.getAllCountByStatus(map)) ;
		return result; 
	}

}
