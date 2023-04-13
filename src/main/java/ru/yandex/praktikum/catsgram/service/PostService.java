package ru.yandex.praktikum.catsgram.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.praktikum.catsgram.exception.PostNotFoundException;
import ru.yandex.praktikum.catsgram.exception.UserNotFoundException;
import ru.yandex.praktikum.catsgram.model.Post;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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


    public Collection<Post> findAll(Integer size, Integer from, String sort) {
        return posts.values().stream().sorted((o1, o2) -> {
            if (sort.equals("asc")) {
                return o1.getCreationDate().compareTo(o2.getCreationDate());
            } else return -1 * o1.getCreationDate().compareTo(o2.getCreationDate());
        })
                .skip(from)
                .limit(size)
                .collect(Collectors.toList());

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
