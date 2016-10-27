package cn.mldn.singup.action.back;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.singup.service.back.INewsServiceBack;
import cn.mldn.singup.vo.News;
import cn.mldn.util.CreateStaticUtil;
import cn.mldn.util.action.AbstractAction;
import cn.mldn.util.split.SplitPageUtil;

@Controller
@RequestMapping("/admin/news/*")
public class NewsActionBack extends AbstractAction {
	@Resource
	private INewsServiceBack newsServiceBack;

	@RequestMapping("list")
	@RequiresRoles("news")
	@RequiresPermissions("news:list")
	public ModelAndView list(HttpServletRequest request) {
		SplitPageUtil spu = new SplitPageUtil(request, "title"); // 可以接收到所有的分页数据
		ModelAndView mav = new ModelAndView(super.getValue("back.news.list.page"));
		// 进行分页信息的查询
		Map<String, Object> result = this.newsServiceBack.list(spu.getColumn(), spu.getKeyWord(), spu.getCurrentPage(),
				spu.getLineSize());
		super.handleSplit(mav, result.get("newsCount"), "公告标题:title|公告摘要:abs", "back.news.list.action", spu);
		mav.addObject("allNews", result.get("allNews")); // 真正需要进行显示的数据的集合
		return mav;
	}

	@RequestMapping("listNone")
	@RequiresRoles("news")
	@RequiresPermissions("news:list")
	public ModelAndView listNone(HttpServletRequest request) {
		SplitPageUtil spu = new SplitPageUtil(request, "title"); // 可以接收到所有的分页数据
		ModelAndView mav = new ModelAndView(super.getValue("back.news.list.page"));
		// 进行分页信息的查询
		Map<String, Object> result = this.newsServiceBack.listNone(spu.getColumn(), spu.getKeyWord(),
				spu.getCurrentPage(), spu.getLineSize());
		super.handleSplit(mav, result.get("newsCount"), "公告标题:title|公告摘要:abs", "back.news.listNone.action", spu);
		mav.addObject("allNews", result.get("allNews")); // 真正需要进行显示的数据的集合
		return mav;
	}

	@RequestMapping("create")
	@RequiresRoles("news")
	@RequiresPermissions("news:list")
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
		String filePath = request.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "news.static" ; 
		new CreateStaticUtil<News>().create(new File(filePath), this.newsServiceBack.listByFlag(1, 13, 1));
		super.print(response, true); 
		return null;
	}  
	
	
	@RequestMapping("editPre")
	@RequiresRoles("news")
	@RequiresPermissions("news:edit")
	public ModelAndView editPre(int nid) {
		ModelAndView mav = new ModelAndView(super.getValue("back.news.edit.page"));
		mav.addAllObjects(this.newsServiceBack.editPre(nid));
		return mav;
	}
	@RequestMapping("remove")
	@RequiresRoles("news")
	@RequiresPermissions("news:edit")
	public ModelAndView remove(HttpServletRequest request, HttpServletResponse response) {
		super.print(response, this.newsServiceBack.remove(super.getBatchIds(request)));
		return null;
	}  

	@RequestMapping("edit")
	@RequiresRoles("news")
	@RequiresPermissions("news:edit")
	public ModelAndView edit(News vo, MultipartFile pic, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getValue("forward.back.page"));
		if (pic != null) {
			if (pic.getSize() > 0) { // 现在有图片上传
				if ("nophoto.gif".equals(vo.getPhoto())) { // 原始没有图片名称
					vo.setPhoto(super.createFileName(pic)); // 创建新的图片名称
				}
			}
			vo.setMid(super.getMid()); // 设置进行新闻创建的管理员信息
		}
		if (this.newsServiceBack.edit(vo)) {
			if (pic != null && pic.getSize() > 0) {
				super.saveFile(pic, vo.getPhoto(), request); // 图片保存
			}
			super.setMsgAndUrl(mav, "vo.edit.success.msg", "back.news.list.action");
		} else {
			super.setMsgAndUrl(mav, "vo.edit.failure.msg", "back.news.list.action");
		}
		return mav;
	}

	@RequestMapping("addPre")
	@RequiresRoles("news")
	@RequiresPermissions("news:add")
	public ModelAndView addPre() {
		ModelAndView mav = new ModelAndView(super.getValue("back.news.add.page"));
		mav.addAllObjects(this.newsServiceBack.addPre());
		return mav;
	}

	@RequestMapping("add")
	@RequiresRoles("news")
	@RequiresPermissions("news:add")
	public ModelAndView add(News vo, MultipartFile pic, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView(super.getValue("forward.back.page"));
		if (pic != null) {
			if (pic.getSize() > 0) {
				String fileName = super.createFileName(pic); // 需要创建一个名称
				vo.setPhoto(fileName); // 保存生成的图片名称
			} else {
				vo.setPhoto("nophoto.gif"); // 保存生成的图片名称
			}
			vo.setMid(super.getMid()); // 设置进行新闻创建的管理员信息
		}
		if (this.newsServiceBack.add(vo)) {
			if (pic != null) {
				if (pic.getSize() > 0) {
					super.saveFile(pic, vo.getPhoto(), request); // 图片保存
				}
			}
			super.setMsgAndUrl(mav, "vo.add.success.msg", "back.news.add.action");
		} else {
			super.setMsgAndUrl(mav, "vo.add.failure.msg", "back.news.add.action");
		}
		return mav;
	}

	@RequestMapping("checkTitle")
	@RequiresRoles("news")
	@RequiresPermissions("news:add")
	public ModelAndView checkTitle(String title, HttpServletResponse response) {
		super.print(response, this.newsServiceBack.getByTitle(title) == null);
		return null;
	}

	@RequestMapping("checkNidAndTitle")
	@RequiresRoles("news")
	@RequiresPermissions("news:edit")
	public ModelAndView checkNidAndTitle(int nid, String title, HttpServletResponse response) {
		News vo = this.newsServiceBack.getByTitle(title); // 根据title取得vo对象
		if (vo == null) {
			super.print(response, true);
		} else {
			super.print(response, vo.getNid().equals(nid));
		}
		return null;
	}

	@Override
	public String getType() {
		return "公告";
	}

	@Override
	public String getFileUploadDir() {
		return "/upload/news/";
	}
}
