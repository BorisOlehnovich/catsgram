package ru.yandex.praktikum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.praktikum.catsgram.exception.UserNotFoundException;
import ru.yandex.praktikum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private UserService userService;
    private final List<Post> posts = new ArrayList<>();

    @Autowired
    public PostService(UserService userService){
        this.userService = userService;
    }


    public List<Post> findAll() {
        return posts;
    }

    public Post createPost(Post post){
        String author = post.getAuthor();
        if (userService.findUserByEmail(author) == null) {
            throw new UserNotFoundException("Пользователь не найден" + author);
        }
        posts.add(post);
        return post;
    }



}
