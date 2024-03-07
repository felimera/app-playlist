package com.project.appplaylist.service;

import com.project.appplaylist.controller.dto.auth.LoginRequest;

public interface LoginService {
    String getTokenGenerator(LoginRequest loginRequest);

}
