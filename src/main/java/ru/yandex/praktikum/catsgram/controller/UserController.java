package ru.yandex.praktikum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.praktikum.catsgram.model.User;
import ru.yandex.praktikum.catsgram.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        userService.createUser(user);
        return user;
    }

    @GetMapping
    public Collection<User> findAll(){
        return userService.findAll();
    }

    @PutMapping
    public User updateUser (@RequestBody User user){
        userService.updateUser(user);
        return user;
    }
}
