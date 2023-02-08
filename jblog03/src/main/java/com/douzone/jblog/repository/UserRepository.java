package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public void joinUser(UserVo vo) {
		sqlSession.insert("user.insertUser",vo);
		sqlSession.insert("user.insertBlog",vo.getId());
		sqlSession.insert("user.insertCategory",vo.getId());
	}
	
	public String findUserById(String id) {
		return sqlSession.selectOne("user.findUserById",id);
	}

	public UserVo findUserByIdAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findUserByIdAndPassword",vo);
	}
}
