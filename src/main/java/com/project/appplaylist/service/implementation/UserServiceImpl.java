package com.project.appplaylist.service.implementation;

import com.project.appplaylist.exception.NotFoundException;
import com.project.appplaylist.model.User;
import com.project.appplaylist.repository.UserRepository;
import com.project.appplaylist.service.UserService;
import com.project.appplaylist.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Integer idUser) {
        return userRepository.findById(idUser).orElseThrow(() -> new RuntimeException(Constants.MESSAGE_USER_NOT_FOUND));
    }

    @Override
    public boolean isExistUser(String email) {
        Optional<User> userOptional = userRepository.findOneByEmail(email);
        if (userOptional.isEmpty())
            throw new NotFoundException(Constants.MESSAGE_USER_NOT_FOUND, "415", HttpStatus.NOT_FOUND);
        return true;
    }
}
