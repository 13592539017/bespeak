package cn.mldn.action.shiro;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.util.action.AbstractAction;
 
@Controller
public class ShiroMemberAction extends AbstractAction {
	@RequestMapping("/successUrl") 
	public ModelAndView successUrl() {
		return new ModelAndView(super.getValue("shiro.successUrl.page"));
	}
	@RequestMapping("/loginUrl") 
	public ModelAndView loginUrl() {
		return new ModelAndView(super.getValue("shiro.loginUrl.page"));
	}
	@RequestMapping("/unauthUrl")
	public ModelAndView unauthUrl() {
		return new ModelAndView(super.getValue("shiro.unauthUrl.page"));
	}
	@Override
	public String getFileUploadDir() {
		return null;
	}
	
}
