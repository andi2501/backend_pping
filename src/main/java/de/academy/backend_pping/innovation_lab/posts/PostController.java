package de.academy.backend_pping.innovation_lab.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api/posts")
public class PostController {


    private PostService postsService;

    public PostController() {
    }

    @Autowired
    public PostController(PostService postsService) {
        this.postsService = postsService;
    }




}
