package ru.yandex.praktikum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.praktikum.catsgram.model.User;
import ru.yandex.praktikum.catsgram.service.UserService;

import java.util.Collection;

@Slf4j
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
        log.info("Запрос на создание нового пользователя принят");
        userService.createUser(user);
        log.debug("Создан пользователь: " + user);
        return user;
    }

    @GetMapping
    public Collection<User> findAll(){
        log.info("Запрос получения списка всех пользователей принят");
        return userService.findAll();
    }

    @PutMapping
    public User updateUser (@RequestBody User user){
        log.info("Запрос обнавления информации о пользователе с принят");
        userService.updateUser(user);
        return user;
    }

    @GetMapping("/{email}")
    public User getUserByEmail (@PathVariable String email){
        log.info("Получен запрос пользователя с Email: " + email);
        return userService.findUserByEmail(email);
    }
}
