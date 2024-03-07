package com.project.appplaylist.controller;

import com.project.appplaylist.controller.dto.auth.LoginRequest;
import com.project.appplaylist.controller.dto.auth.LoginResponse;
import com.project.appplaylist.exception.ResponseMessageException;
import com.project.appplaylist.service.LoginService;
import com.project.appplaylist.service.UserService;
import com.project.appplaylist.util.CadenaUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/login")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {
    private UserService userService;

    private LoginService loginService;

    @Autowired
    public LoginController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        log.info("Creating LoginRequest : {}", loginRequest);
        if (bindingResult.hasErrors())
            throw new ResponseMessageException("401-01", "Error creating client.", CadenaUtil.formatMessage(bindingResult), HttpStatus.BAD_REQUEST);

        userService.isExistUser(loginRequest.getEmail());

        String jwt = loginService.getTokenGenerator(loginRequest);
        // Aditional logic can here be added here if needed.
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
}
