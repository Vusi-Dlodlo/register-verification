package com.twt.registerverification.service;

import com.twt.registerverification.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> saveUser(User user);

    ResponseEntity<?> confirmEmail(String token);

}
