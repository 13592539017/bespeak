package cn.mldn.singup.service.back.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
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
	@RequiresRoles("member")
	@RequiresPermissions("member:edit") 
	@Override
	public boolean editLocked(String mid, int locked) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("mid", mid) ;
		map.put("locked", locked) ;
		return this.memberDAO.doUpdateLocked(map); 
	}
	
	
	@RequiresRoles("member")
	@RequiresPermissions("member:edit") 
	@Override
	public boolean editPasswordByAdmin(String mid, String password) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("mid", mid) ;
		map.put("password", password) ;
		return this.memberDAO.doUpdatePasswordByAdmin(map); 
	}
	
	@RequiresRoles("member")
	@RequiresPermissions("member:list") 
	@Override 
	public List<Member> list() {
		return this.memberDAO.findAll(); 
	}
	
	@Override
	@RequiresUser 
	public boolean editPassword(String mid, String oldPassword, String newPassword) {
		Member vo = this.memberDAO.findById(mid) ;	// 根据已有的用户编号取得用户的完整信息
		if (vo == null) {	// 该用户已经没有了
			return false ;
		} 
		if (oldPassword.equals(vo.getPassword())) {	// 判断旧密码是否相等
			Map<String,Object> params = new HashMap<String,Object>() ;
			params.put("mid", mid) ;
			params.put("newPassword", newPassword) ;
			return this.memberDAO.doUpdatePassword(params) ; 
		}
		return false;
	}
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
