package com.douzone.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostRepository postRepository;
	
	public Map<String, Object> getBlogIndexPage(String id) {
		BlogVo blogVo = blogRepository.findBlogInfo(id);
		
		List<CategoryVo> categoryList = categoryRepository.findAllCategory(id);
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName("기본");
		categoryVo.setId(id);
		
		Long defaultCategoryNo = categoryRepository.findCategoryNoByNameAndId(categoryVo);
		
		List<PostVo> postList = postRepository.findPostByCategoryNo(defaultCategoryNo);
		
		// 블로그 정보 담기
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("blogvo", blogVo);
		map.put("categorylist", categoryList);
		map.put("postlist", postList);
		
		return map;
	}
	
	public BlogVo getBlogInfo(String id) {
		return blogRepository.findBlogInfo(id);
	}

	public void updateBlog(BlogVo vo) {
		blogRepository.updateBlog(vo);
	}
	
	public List<CategoryVo> getCategory(String id) {
		return categoryRepository.findAllCategory(id);
	}
	
	public void addCategory(CategoryVo vo) {
		categoryRepository.insertCategory(vo);
	}

	public void deleteCategory(Long no) {
		categoryRepository.deleteCategory(no);
		
	}

	public void addPost(PostVo vo) {
		postRepository.insertPost(vo);
		
	}
}