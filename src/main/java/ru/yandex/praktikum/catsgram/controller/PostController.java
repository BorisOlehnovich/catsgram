package ru.yandex.praktikum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.praktikum.catsgram.model.Post;
import ru.yandex.praktikum.catsgram.service.PostService;

import java.util.Collection;
import java.util.List;

@RestController
@Slf4j
public class PostController {
    private PostService postService;
    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/posts")
    public Collection<Post> findAll() {
        log.info("Запрос всех постов получен");
        return postService.findAll();
    }

    @GetMapping("/posts/{id}")
    public Post getPostById(@PathVariable int id){
        log.info("Получен запрос поста с ID = " + id);
        return postService.getPostById(id);
    }

    @PostMapping(value = "/post")
    public Post createPost(@RequestBody Post post){
        log.info("Запрос на создание поста принят");
        postService.createPost(post);
        log.debug("Создан пост: " + post);
        return post;
    }


}
