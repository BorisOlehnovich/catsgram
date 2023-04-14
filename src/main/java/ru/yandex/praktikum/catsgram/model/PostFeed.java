package ru.yandex.praktikum.catsgram.model;

import lombok.Data;

import java.util.List;

@Data
public class PostFeed {
    private String sort;
    private Integer size;
    private List<String> friendsEmails;
}
