package de.academy.backend_pping.innovation_lab.posts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void newPost(){
        Post post = new Post();
        assertNotNull(post);
    }

}