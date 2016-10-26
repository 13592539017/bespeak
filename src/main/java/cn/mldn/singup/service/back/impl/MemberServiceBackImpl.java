package cn.mldn.singup.service.back.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
	
	@Override
	@RequiresRoles("member")
	@RequiresPermissions("member:edit") 
	public Map<String, Object> editPre(String mid) {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allRoles", this.roleDAO.findAll()) ;
		map.put("member", this.memberDAO.findById(mid)) ;
		map.put("memberRoles", this.memberDAO.findAllRoleByMember(mid)) ;
		return map ; 
	}
	@Override
	@RequiresRoles("member")
	@RequiresPermissions("member:edit")
	public boolean edit(Member vo, Set<Integer> rid) {
		if (this.memberDAO.findById(vo.getMid()) != null) {	// 要修改的用户存在
			if (this.memberDAO.doUpdate(vo)) {	// 进行用户数据的更新
				if (this.memberDAO.doRemoveMemberAndRole(vo.getMid())) { // 删除掉已经存在的关系
					// 随后需要向member_role数据表里面保存有对应的关系数据
					Iterator<Integer> iter = rid.iterator() ;
					while (iter.hasNext()) {
						Map<String,Object> map = new HashMap<String,Object>() ;
						map.put("mid", vo.getMid()) ;
						map.put("rid", iter.next()) ;
						this.memberDAO.doCreateMemberAndRole(map) ;
					}
					return true ;
				}
			}
		}
		return false;
	} 
	
	
	@Override
	@RequiresRoles("member")
	@RequiresPermissions("member:add")
	public boolean add(Member vo, Set<Integer> rid) {
		if (this.memberDAO.findById(vo.getMid()) == null) {	// 当前要追加的用户id不存在
			vo.setRegdate(new Date()); 	// 用户的创建日期为今天
			vo.setSflag(0); 	// 创建的都是普通管理员
			vo.setLocked(0); 	// 新创建的管理员不可能被锁定
			if (this.memberDAO.doCreate(vo)) {	// 现在已经保存了用户的信息
				// 随后需要向member_role数据表里面保存有对应的关系数据
				Iterator<Integer> iter = rid.iterator() ;
				while (iter.hasNext()) {
					Map<String,Object> map = new HashMap<String,Object>() ;
					map.put("mid", vo.getMid()) ;
					map.put("rid", iter.next()) ;
					this.memberDAO.doCreateMemberAndRole(map) ;
				}
				return true ;
			}
		}
		return false;
	} 
	@Override
	@RequiresRoles("member")
	@RequiresPermissions("member:add") 
	public Map<String, Object> addPre() {
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("allRoles", this.roleDAO.findAll()) ;
		return map ; 
	}
	
	
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
