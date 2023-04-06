package ru.yandex.praktikum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.praktikum.catsgram.exception.InvalidEmailException;
import ru.yandex.praktikum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.praktikum.catsgram.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public Collection<User> findAll() {
        return users.values();
    }

    public User createUser(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()){
            throw new InvalidEmailException("Поле Email не должно быть пустым");
        }
        if (users.containsKey(user.getEmail())) {
            throw new UserAlreadyExistException("Пользователь с таким Email уже существует");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User updateUser(User user){
        if (user.getEmail() == null || user.getEmail().isBlank()){
            throw new InvalidEmailException("Поле Email не должно быть пустым");
        }
        users.put(user.getEmail(), user);
        return user;
    }

    public User findUserByEmail (String email) {
        if (email == null) {
            return null;
        }
        return users.get(email);
    }
}
