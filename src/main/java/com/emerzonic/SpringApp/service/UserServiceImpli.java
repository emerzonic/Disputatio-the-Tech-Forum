package com.emerzonic.SpringApp.service;

import com.emerzonic.SpringApp.DAO.UserRepository;
import com.emerzonic.SpringApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public void createUser(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);

	}

    @Override
    public User findByUserName(String userName) {
        return userRepository.findById(userName).orElse(null);
    }
}
