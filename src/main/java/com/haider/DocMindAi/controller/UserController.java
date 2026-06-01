package com.haider.DocMindAi.controller;

import com.haider.DocMindAi.dtos.request.UserRequest;
import com.haider.DocMindAi.dtos.response.UserResponse;
import com.haider.DocMindAi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserRequest request) {
        UserResponse saved = userService.addUser(request);
        return ResponseEntity.ok().body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        UserResponse userResponse = userService.getById(id);
        return ResponseEntity.ok().body(userResponse);
    }
}
