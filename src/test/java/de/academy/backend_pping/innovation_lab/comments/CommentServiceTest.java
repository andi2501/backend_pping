package de.academy.backend_pping.innovation_lab.comments;

import de.academy.backend_pping.buddy_core.user.UserEntity;
import de.academy.backend_pping.buddy_core.user.UserService;
import de.academy.backend_pping.innovation_lab.posts.Post;
import de.academy.backend_pping.innovation_lab.posts.PostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {

    RandomGenerator randomGenerator;
    CommentService commentService;
    UserService userService;
    PostService postService;

    Post post;

    @Autowired
    public CommentServiceTest(CommentService commentService, UserService userService, PostService postService) {
        this.randomGenerator = new Random();
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
    }

    @BeforeEach
    void setUp() {
        // new Post
        String username = "Benni" + randomGenerator.nextInt();
        UserEntity authorPost = userService.registerUser(username,"");
        Post post = new Post(authorPost,"Post of Comments","Comments sollten einem Post angehöhren");
        this.post = postService.createPost(post);
    }

    @AfterEach
    void tearDown() {

        // clean up
        postService.deletePostById(post.getId());
        userService.deleteById(post.getAuthor().getId());
    }



    @Test
    void findCommentsByPostIdOrderByCreationTimestampAsc() {

        // generate 3 Comments for post
        String usernameComment = "Chan" + randomGenerator.nextInt();
        UserEntity authorComment = userService.registerUser(usernameComment,"");
        Comment comment1 = commentService.createComment(authorComment.getId(),post.getId(),"Kommentar Chan");

        usernameComment = "Cori" + randomGenerator.nextInt();
        authorComment = userService.registerUser(usernameComment,"");
        Comment comment2 = commentService.createComment(authorComment.getId(),post.getId(),"Kommentar Cori");

        usernameComment = "CHristiana" + randomGenerator.nextInt();
        authorComment = userService.registerUser(usernameComment,"");
        Comment comment3 = commentService.createComment(authorComment.getId(),post.getId(),"Kommentar Christiane");

        // Test number of comments
        assertEquals(3,commentService.findCommentsByPostIdOrderByCreationTimestampAsc(post.getId()).size());
        // Test ascending timestamp order of comments
        LocalDateTime localDateTime1 = comment1.getCreationTimestamp();
        LocalDateTime localDateTime2 = comment2.getCreationTimestamp();
        LocalDateTime localDateTime3 = comment3.getCreationTimestamp();

        assertTrue(localDateTime1.isBefore(localDateTime2));
        assertTrue(localDateTime2.isBefore(localDateTime3));

        // cleanup
        commentService.deleteCommentById(comment1.getId());
        commentService.deleteCommentById(comment2.getId());
        commentService.deleteCommentById(comment3.getId());

        userService.deleteById(comment1.getAuthor().getId());
        userService.deleteById(comment2.getAuthor().getId());
        userService.deleteById(comment3.getAuthor().getId());

    }

    @Test
    void createComment() {

        // new Comment to Post
        String usernameComment = "Sabrin" + randomGenerator.nextInt();
        UserEntity authorComment = userService.registerUser(usernameComment,"");
        Comment comment = commentService.createComment(
                authorComment.getId(),
                post.getId(),
                "Kommentare sind ne tolle Sache");

        // author id of comment is correct
        assertEquals(authorComment.getId(),comment.getAuthor().getId());
        // post id of comment is correct
        assertEquals(post.getId(),comment.getPost().getId());

        // cleanup

        commentService.deleteCommentById(comment.getId());
        userService.deleteById(authorComment.getId());

    }

    @Test
    void deleteCommentById() {

        //generate Comment
        String usernameComment = "Silvia" + randomGenerator.nextInt();
        UserEntity authorComment = userService.registerUser(usernameComment,"");

        Comment comment = commentService.createComment(authorComment.getId(), post.getId(), "Dieses Comment wird nicht lange existieren.");
        //delete comment
        commentService.deleteCommentById(comment.getId());
        // check if not exists anymore
        List<Comment> comments = commentService.findCommentsByPostIdOrderByCreationTimestampAsc(post.getId());
        assertEquals(0,comments.size());

        // cleanup
        userService.deleteById(authorComment.getId());


    }
}