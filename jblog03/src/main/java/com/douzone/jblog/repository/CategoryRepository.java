package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVo> findAllCategory(String id) {
		return sqlSession.selectList("category.findAllCategory", id);
	}
	
	public Long findCategoryNoByNameAndId(CategoryVo vo) {
		return sqlSession.selectOne("category.findCategoryNoByNameAndId", vo);
	}
	
	public void insertCategory(CategoryVo vo) {
		sqlSession.insert("category.insertCategory", vo);
	}

	public void deleteCategory(Long no) {
		sqlSession.delete("category.deleteCategory", no);
	}
}
