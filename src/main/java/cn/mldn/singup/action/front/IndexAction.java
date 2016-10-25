package cn.mldn.singup.action.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.util.action.AbstractAction;
@Controller 
public class IndexAction extends AbstractAction {
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView(super.getValue("front.index.page"));
	}
	@Override
	public String getFileUploadDir() {
		return null;
	}

}
