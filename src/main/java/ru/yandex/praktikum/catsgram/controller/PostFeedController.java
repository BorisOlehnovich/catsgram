package ru.yandex.praktikum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.praktikum.catsgram.exception.IncorrectParameterException;
import ru.yandex.praktikum.catsgram.model.Post;
import ru.yandex.praktikum.catsgram.model.PostFeed;
import ru.yandex.praktikum.catsgram.service.PostService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("feed/friends")
public class PostFeedController {
    private final PostService postService;
    @Autowired
    public PostFeedController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getFriendsFeed (@RequestBody PostFeed postFeed){
        if(!(postFeed.getSort().equals("asc") || postFeed.getSort().equals("desc"))){
            throw new IncorrectParameterException("sort");
        }
        if (postFeed.getSize() == null || postFeed.getSize() <= 0) {
            throw new IncorrectParameterException("size");
        }
        if (postFeed.getFriendsEmails().isEmpty()) {
            throw new IncorrectParameterException("friendsEmails");
        }

        List<Post> result = new ArrayList<>();
        for (String email : postFeed.getFriendsEmails()){
            result.addAll(postService.findAllByEmail(email, postFeed.getSize(), postFeed.getSort()));
        }
        return result;
    }
}
