package com.douzone.jblog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Controller
@RequestMapping("{id}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private UserService userService;
	@Autowired
	private FileuploadService fileuploadService;

	@RequestMapping("")
	public String myBlog(
			@PathVariable("id") String id, 
			@RequestParam(value="category", required=true, defaultValue="기본") String category,
			@RequestParam(value="postno", required=true, defaultValue="0") Long postNo,
			Model model) {
		
		if(userService.findUser(id)) {
			return "redirect:/";
		}
		
		Map<String, Object> map = blogService.getBlogIndexPage(id, category, postNo);

		model.addAllAttributes(map);

		return "blog/main";
	}

	// @RequestParam(value="no", required=true, defaultValue="1") int pageNo
	
	@Auth
	@RequestMapping("/admin")
	public String blogAdmin(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("id", id);
		model.addAttribute("blogvo", blogVo);

		return "blog/admin-basic";
	}

	@Auth
	@RequestMapping("/admin/update")
	public String update(@PathVariable("id") String id, BlogVo vo, @RequestParam("file") MultipartFile file) {
		String url = fileuploadService.restore(file);
		vo.setId(id);

		if (url != null) {
			vo.setProfile(url);
		}

		blogService.updateBlog(vo);

		return "redirect:/" + id + "/admin";
	}
	
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
		List<CategoryVo> vo = blogService.getCategory(id);
		model.addAttribute("categorylist", vo);
		
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("blogvo", blogVo);
		
		model.addAttribute("id", id);

		return "blog/admin-category";
	}

	@Auth
	@RequestMapping("/admin/category/add")
	public String addCategory(@PathVariable("id") String id, CategoryVo vo) {
		blogService.addCategory(vo);

		return "redirect:/" + id + "/admin/category";
	}
	
	@Auth
	@RequestMapping("/admin/category/delete/{no}/{count}/{defaultView}")
	public String deleteCategory(
			@PathVariable("id") String id,
			@PathVariable("no") Long no, 
			@PathVariable("count") Long count, 
			@PathVariable("defaultView") String defaultView,
			CategoryVo vo, 
			Model model) {
		
		if(defaultView.equals("Y")) {
        	model.addAttribute("msg","기본 카테고리는 삭제 할 수 없습니다.");
        	model.addAttribute("url","/" + id + "/admin/category");
        	return "tools/redirect";
		}
		
		if(count > 0) {
        	model.addAttribute("msg","포스트가 한개라도 있으면 삭제 할 수 없습니다.");
        	model.addAttribute("url","/" + id + "/admin/category");
        	return "tools/redirect";
		}
		
		blogService.deleteCategory(no);
    	model.addAttribute("msg","카테고리가 삭제 되었습니다.");
    	model.addAttribute("url","/" + id + "/admin/category");
    	return "tools/redirect";
	}
	
	@Auth
	@RequestMapping("/admin/category/change/{no}")
	public String changeDefaultCategory(
			@PathVariable("id") String id, 
			@PathVariable("no") Long no ) {
		blogService.changeDefaultCategory(no);

		return "redirect:/" + id + "/admin/category";
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Model model) {
		List<CategoryVo> vo = blogService.getCategory(id);
		model.addAttribute("categorylist", vo);
		
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("blogvo", blogVo);
		
		model.addAttribute("id", id);

		return "blog/admin-write";
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, PostVo vo) {
		blogService.addPost(vo);

		return "redirect:/" + id + "/admin/write";
	}
}