package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public void insertBlog(String id) {
		sqlSession.insert("blog.insertBlog", id);
	}
	
	public BlogVo findBlogInfo(String id) {
		return sqlSession.selectOne("blog.findBlogInfo", id);
	}

	public void updateBlog(BlogVo vo) {
		sqlSession.update("blog.updateBlog", vo);
	}
}
