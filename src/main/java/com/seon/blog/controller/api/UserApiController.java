package com.seon.blog.controller.api;

import com.seon.blog.model.RoleType;
import com.seon.blog.model.User;
import com.seon.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    private UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user")
    public ResponseEntity<Integer> save(@RequestBody User user){
        user.setRole(RoleType.USER);
        userService.memberJoin(user);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @PostMapping("/api/user/login")
    public ResponseEntity<Integer> login(@RequestBody User user, HttpSession session) {
        User principal = userService.memberLogin(user);
        if(principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
