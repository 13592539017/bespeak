package cn.mldn.singup.action.front;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.singup.service.front.INewsServiceFront;
import cn.mldn.util.action.AbstractAction;
import cn.mldn.util.split.SplitPageUtil;

@RequestMapping("/news/*")
@Controller
public class NewsActionFront extends AbstractAction {
	@Resource
	private INewsServiceFront newsServiceFront;

	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request) {
		SplitPageUtil spu = new SplitPageUtil(request, null); // 可以接收到所有的分页数据
		ModelAndView mav = new ModelAndView(super.getValue("front.news.list.page"));
		// 进行分页信息的查询
		Map<String, Object> result = this.newsServiceFront.list(null, null, spu.getCurrentPage(), spu.getLineSize());
		super.handleSplit(mav, result.get("newsCount"), null, "front.news.list.action", spu);
		mav.addObject("allNews", result.get("allNews")); // 真正需要进行显示的数据的集合
		return mav;
	} 

	@RequestMapping("show")
	public ModelAndView show(int nid) {
		ModelAndView mav = new ModelAndView(super.getValue("front.news.show.page"));
		mav.addObject("news", this.newsServiceFront.show(nid));
		return mav;
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
