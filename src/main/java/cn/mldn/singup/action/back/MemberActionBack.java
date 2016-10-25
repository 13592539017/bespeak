package cn.mldn.singup.action.back;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.singup.service.back.IMemberServiceBack;
import cn.mldn.util.action.AbstractAction;
import cn.mldn.util.encrypt.MyPasswordEncrypt;

@Controller
@RequestMapping("/admin/member/*")
public class MemberActionBack extends AbstractAction {
	@Resource
	private IMemberServiceBack memberServiceBack;

	@RequestMapping("editPasswordByAdmin")
	@RequiresUser
	@RequiresRoles("member")
	@RequiresPermissions("member:edit")
	public ModelAndView editPasswordByAdmin(String mid, String password, HttpServletResponse response) {
		super.print(response,
				this.memberServiceBack.editPasswordByAdmin(mid, MyPasswordEncrypt.encryptPassword(password)));
		return null;
	}

	@RequestMapping("editLocked")
	@RequiresUser
	@RequiresRoles("member") 
	@RequiresPermissions("member:edit")
	public ModelAndView editLocked(String mid, int locked, HttpServletResponse response) {
		super.print(response,
				this.memberServiceBack.editLocked(mid, locked)); 
		return null;
	} 

	@RequestMapping("list")
	@RequiresUser
	@RequiresRoles("member")
	@RequiresPermissions("member:list")
	public ModelAndView list() {
		ModelAndView mav = new ModelAndView(super.getValue("back.member.list.page"));
		mav.addObject("allMembers", this.memberServiceBack.list());
		return mav;
	}

	@RequestMapping("editPassword")
	@RequiresUser
	public ModelAndView editPassword(String newpassword, String oldpassword) {
		ModelAndView mav = new ModelAndView(super.getValue("forward.back.page"));
		// 对接收到的密码进行加密处理
		String newPassword = MyPasswordEncrypt.encryptPassword(newpassword);
		String oldPassword = MyPasswordEncrypt.encryptPassword(oldpassword);
		if (this.memberServiceBack.editPassword(super.getMid(), oldPassword, newPassword)) {
			super.setMsgAndUrl(mav, "back.member.edit.password.success.msg", "front.index.action");
		} else {
			super.setMsgAndUrl(mav, "back.member.edit.password.failure.msg", "front.index.action");
		}
		// super.logout();
		return mav;
	}

	@RequestMapping("editPasswordPre")
	@RequiresUser
	public ModelAndView editPasswordPre() {
		return new ModelAndView(super.getValue("back.member.editpassword.page"));
	}

	@Override
	public String getFileUploadDir() {
		return null;
	}

}
