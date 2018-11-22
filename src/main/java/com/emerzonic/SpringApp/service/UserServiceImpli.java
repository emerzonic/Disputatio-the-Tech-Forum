package com.emerzonic.SpringApp.service;

import com.emerzonic.SpringApp.DAO.UserRepository;
import com.emerzonic.SpringApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpli implements UserService {
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpli(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


	@Override
	@Transactional
	public void addUser(User user) {
		userRepository.save(user);

	}

    @Override
    public boolean checkIfUserExist(Integer userId) {
        return userRepository.existsById(userId);
    }

    @Override
	@Transactional
	public User getUser(Integer userId) {
	    return userRepository.findById(userId).orElse(null);
	}
	
	
}
