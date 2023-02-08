package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public boolean join(UserVo vo) {
		if(userRepository.findUserById(vo.getId()) == null) {
			userRepository.joinUser(vo);
			return true;
		}
		return false;
	}

	public UserVo getUser(UserVo vo) {
		return userRepository.findUserByIdAndPassword(vo);
	}
}
