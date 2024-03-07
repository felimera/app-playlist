package com.project.appplaylist.service;

import com.project.appplaylist.controller.dto.SignUpDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    boolean createUser(SignUpDto signUpDto);
}
