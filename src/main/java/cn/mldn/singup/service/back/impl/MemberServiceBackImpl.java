package cn.mldn.singup.service.back.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.singup.dao.IMemberDAO;
import cn.mldn.singup.service.back.IMemberServiceBack;
import cn.mldn.singup.service.back.abs.AbstractServiceBack;
import cn.mldn.singup.vo.Member;
@Service
public class MemberServiceBackImpl extends AbstractServiceBack implements IMemberServiceBack {
	@Resource 
	private IMemberDAO memberDAO ;
	@Override
	public Member get(String mid) {
		return this.memberDAO.findById(mid); 
	}

}
