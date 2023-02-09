package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> findPostByCategoryNo(Long no) {
		return sqlSession.selectList("post.findPostByCategoryNo", no);
	}

	public void insertPost(PostVo vo) {
		sqlSession.insert("post.insertPost", vo);
	}

	public PostVo findPostByPostNo(Long postNo) {
		return sqlSession.selectOne("post.findPostByPostNo", postNo);
	}

	public PostVo findLatestPostByCategoryNo(Long categoryNo) {
		return sqlSession.selectOne("post.findLatestPostByCategoryNo", categoryNo);
	}
}
