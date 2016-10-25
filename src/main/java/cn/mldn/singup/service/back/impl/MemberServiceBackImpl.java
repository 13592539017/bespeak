package cn.mldn.singup.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.singup.dao.IActionDAO;
import cn.mldn.singup.dao.IMemberDAO;
import cn.mldn.singup.dao.IRoleDAO;
import cn.mldn.singup.service.back.IMemberServiceBack;
import cn.mldn.singup.service.back.abs.AbstractServiceBack;
import cn.mldn.singup.vo.Member;

@Service
public class MemberServiceBackImpl extends AbstractServiceBack implements IMemberServiceBack {
	@Resource
	private IMemberDAO memberDAO;
	@Resource
	private IRoleDAO roleDAO;
	@Resource
	private IActionDAO actionDAO;

	@Override
	public Member get(String mid) {
		return this.memberDAO.findById(mid);
	}

	@Override
	public Map<String, Object> listAuthByMember(String mid) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allRoles", this.roleDAO.findAllRoleFlag(mid)) ;
		map.put("allActions", this.actionDAO.findAllActionFlag(mid)) ;
		return map; 
	} 

}
