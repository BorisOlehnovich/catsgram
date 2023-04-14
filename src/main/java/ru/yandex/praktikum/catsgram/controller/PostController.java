package ru.yandex.praktikum.catsgram.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.praktikum.catsgram.exception.IncorrectParameterException;
import ru.yandex.praktikum.catsgram.model.Post;
import ru.yandex.praktikum.catsgram.service.PostService;

import java.util.Collection;

@RestController
@Slf4j
public class PostController {
    private PostService postService;
    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/posts")
    public Collection<Post> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
                                    @RequestParam(value = "size", defaultValue = "10", required = false) Integer size,
                                    @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        log.info("Запрос последних " + size + " постов получен, " + "порядок сортировки: " + sort);
        if(!(sort.equals("asc") || sort.equals("desc"))){
            throw new IncorrectParameterException("sort");
        }
        if (page < 0) {
            throw new IncorrectParameterException("page");
        }
        if(page < 0 || size <= 0){
            throw new IncorrectParameterException("size");
        }

        Integer from = page * size;
        return postService.findAll(size, from, sort);
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
