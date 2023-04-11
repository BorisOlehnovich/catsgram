package ru.yandex.praktikum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.praktikum.catsgram.exception.PostNotFoundException;
import ru.yandex.praktikum.catsgram.exception.UserNotFoundException;
import ru.yandex.praktikum.catsgram.model.Post;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PostService {
    private int id = 0;
    private UserService userService;
    private final Map<Integer, Post> posts = new HashMap<>();

    @Autowired
    public PostService(UserService userService){
        this.userService = userService;
    }

    private int generateId(){
        return ++id;
    }


    public Collection<Post> findAll() {
        log.debug("Текущее количество постов {}", posts.size());
        return posts.values();
    }

    public Post createPost(Post post){
        String author = post.getAuthor();
        if (userService.findUserByEmail(author) == null) {
            log.error("При добавлении нового поста пользователь не был найден");
            throw new UserNotFoundException("Пользователь не найден" + author);
        }
        post.setId(generateId());
        posts.put(post.getId(), post);
        log.debug("Добывлен пост с Id = " + post.getId());
        return post;
    }

    public Post getPostById (int id){
        if (!posts.containsKey(id)) {
            throw new PostNotFoundException("Пост с таким ID не найден");
        }
        log.debug("Будет передан пост с индитификационным номером = " + id);
        return posts.get(id);
    }



}
