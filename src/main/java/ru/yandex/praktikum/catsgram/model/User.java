package ru.yandex.praktikum.catsgram.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Data
public class User {
    private String email;
    private String nickname;
    private LocalDate birthDate;
}
