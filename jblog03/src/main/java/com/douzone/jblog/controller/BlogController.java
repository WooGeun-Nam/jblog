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

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@RequestMapping("{id}")
@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	private FileuploadService fileuploadService;

	@RequestMapping("")
	public String myBlog(@PathVariable("id") String id, Model model) {
		Map<String, Object> map = blogService.getBlogIndexPage(id);

		model.addAllAttributes(map);

		return "blog/main";
	}

	@RequestMapping("/admin")
	public String blogAdmin(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlogInfo(id);
		model.addAttribute("id", id);
		model.addAttribute("blogvo", blogVo);

		return "blog/admin-basic";
	}

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

	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, Model model) {
		List<CategoryVo> vo = blogService.getCategory(id);
		
		model.addAttribute("categorylist", vo);
		model.addAttribute("id", id);

		return "blog/admin-category";
	}

	@RequestMapping("/admin/category/add")
	public String addCategory(@PathVariable("id") String id, CategoryVo vo) {
		blogService.addCategory(vo);

		return "redirect:/" + id + "/admin/category";
	}
	
	@RequestMapping("/admin/category/delete/{no}")
	public String deleteCategory(@PathVariable("id") String id, @PathVariable("no") Long no, CategoryVo vo) {
		blogService.deleteCategory(no);

		return "redirect:/" + id + "/admin/category";
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.GET)
	public String adminWrite(@PathVariable("id") String id, Model model) {
		List<CategoryVo> vo = blogService.getCategory(id);
		
		model.addAttribute("categorylist", vo);
		model.addAttribute("id", id);

		return "blog/admin-write";
	}
	
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, PostVo vo) {
		blogService.addPost(vo);

		return "redirect:/" + id + "/admin/write";
	}
}