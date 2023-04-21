package de.academy.backend_pping.innovation_lab.posts;

import de.academy.backend_pping.buddy_core.user.UserEntity;
import de.academy.backend_pping.buddy_core.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    PostService postService;
    UserService userService;

    RandomGenerator randomGenerator;

    @Autowired
    public PostServiceTest(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
        this.randomGenerator = new Random();
    }


    @Test
    void createPost() {
        String testName = "Chris" + randomGenerator.nextInt();
        // Create Post with new User
        UserEntity author = userService.registerUser(testName,"");
        Post post = new Post(author,"PostCreator","JUnit sollte für alle Services genutzt werden.");
        Post postReturn = postService.createPost(post);

        // Create Post with existing User (only mock test for User)
        UserEntity authorExisting = userService.findById(author.getId());
        Post post2 = new Post(authorExisting,"MeinZweiter Post","Ipsum larum...");
        Post postReturn2 = postService.createPost(post2);



    }

    @Test
    void findPostsByAuthorIdOrderByCreationTimestampDesc(){

        // no author found
        List<Post> postListEmpty = postService.findPostsByAuthorIdOrderByCreationTimestampDesc(-1);
        assertEquals(0,postListEmpty.size());

        // Create Post with new User
        String testName = "Tim" + randomGenerator.nextInt();
        UserEntity author = userService.registerUser(testName,"");
        Post post = new Post(author,"findPost1","findPost1");
        postService.createPost(post);
        Post post2 = new Post(author,"findPost2","findPost2");
        postService.createPost(post2);

        List<Post> postListExist = postService.findPostsByAuthorIdOrderByCreationTimestampDesc(author.getId());
        assertEquals(2,postListExist.size());


        // Test descending Order of timestamp from returned list of posts
        LocalDateTime localDateTimePost2 = postListExist.get(0).getCreationTimestamp(); //shall be last one
        LocalDateTime localDateTimePost1 = postListExist.get(1).getCreationTimestamp();
        assertTrue(localDateTimePost2.isAfter(localDateTimePost1));
    }


    @Test
    void findPostById() {

        // Case post found
        String testName = "Thomas" + randomGenerator.nextInt();
        UserEntity author = userService.registerUser(testName,"");
        Post post = new Post(author,"findPostId1","findPostId1");
        Post postReturn = postService.createPost(post);

        Post postFindById = postService.findPostById(postReturn.getId());
        assertEquals(postReturn,postFindById);

//        // Case no post --> null
        assertNull(postService.findPostById(-1L));



    }
}