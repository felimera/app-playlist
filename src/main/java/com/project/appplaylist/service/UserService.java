package com.project.appplaylist.service;

import com.project.appplaylist.model.User;

public interface UserService {
    User findById(Integer idUser);

    boolean isExistUser(String email);
}
