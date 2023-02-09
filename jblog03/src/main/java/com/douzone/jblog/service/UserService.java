package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	public boolean join(UserVo vo) {
		if(findUser(vo.getId())) {
			userRepository.joinUser(vo);
			blogRepository.insertBlog(vo.getId());
			categoryRepository.insertDefaultCategory(vo.getId());
			return true;
		}
		return false;
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findUserByIdAndPassword(vo);
	}
	
	public boolean findUser(String id) {
		if (userRepository.findUserById(id) == null) {
			return true;
		}
		return false;
	}
}
