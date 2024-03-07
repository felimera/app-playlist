package com.project.appplaylist.service.implementation;

import com.project.appplaylist.controller.dto.SignUpDto;
import com.project.appplaylist.controller.mapper.UserMapper;
import com.project.appplaylist.exception.BusinessException;
import com.project.appplaylist.model.User;
import com.project.appplaylist.repository.UserRepository;
import com.project.appplaylist.service.AuthService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public boolean createUser(SignUpDto signUpDto) {

        if (this.isEmailExist(signUpDto.getEmail())) {
            throw new BusinessException("300", HttpStatus.CONFLICT, "This email already exists.");
        }

        String hashPassword = passwordEncoder.encode(signUpDto.getPassword());
        User user = UserMapper.INSTANCE.toSignUp(signUpDto);
        user.setPassword(hashPassword);
        userRepository.save(user);
        return true;
    }

    private boolean isEmailExist(String email) {
        return userRepository.findOneByEmail(email).isPresent();
    }
}
