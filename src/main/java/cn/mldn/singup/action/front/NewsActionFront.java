package cn.mldn.singup.action.front;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.singup.service.front.INewsServiceFront;
import cn.mldn.util.action.AbstractAction;
@RequestMapping("/news/*")
@Controller
public class NewsActionFront extends AbstractAction {
	@Resource
	private INewsServiceFront newsServiceFront ;
	@RequestMapping("show") 
	public ModelAndView show(int nid) {
		ModelAndView mav = new ModelAndView(super.getValue("front.news.show.page")) ;
		mav.addObject("news",this.newsServiceFront.show(nid)) ;
		return mav ;
	}
	@Override
	public String getType() {
		return null;
	}
	@Override
	public String getFileUploadDir() {
		return null;
	}
}
