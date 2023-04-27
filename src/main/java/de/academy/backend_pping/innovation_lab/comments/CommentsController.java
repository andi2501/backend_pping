package de.academy.backend_pping.innovation_lab.comments;

import de.academy.backend_pping.buddy_core.session.SessionService;
import de.academy.backend_pping.innovation_lab.posts.PostDTO;
import de.academy.backend_pping.innovation_lab.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/comments")
public class CommentsController {

    private CommentService commentService;
    private PostService postService;

    private SessionService sessionService;


    public CommentsController() {
    }

    @Autowired
    public CommentsController(CommentService commentService, PostService postService, SessionService sessionService) {
        this.commentService = commentService;
        this.postService = postService;
        this.sessionService = sessionService;
    }

    /**
     * API accepts a comment to be saved in Database.<br>
     * It returns the saved comment to provide an id and timestamp for later use.
     *
     * @param commentDTO DTO to get Data via Requestbody for comments
     * @return saved entity
     */
    @PostMapping("/create")
    public @ResponseBody CommentDTO createComment(
            @CookieValue(value = "session", defaultValue = "8484beef-3a5c-49ad-a18b-dde4a39033c9") String sessionToken,
            @RequestBody CommentDTO commentDTO) {

        // default user 1 testuser
        long authorID = sessionService.getUserId(sessionToken);
        long postID = commentDTO.getPostID();
        String text = commentDTO.getText();

        Comment comment = commentService.createComment(authorID, postID, text);

        if (comment == null) {
            return null;
        } else {
            return new CommentDTO(comment);
        }
    }

    /**
     * API provides a list of comments based on the corresponding post id
     *
     * @param id Id of post
     * @return List of Comments
     */
    @GetMapping("/post{id}")
    public List<CommentDTO> findCommentsByPostIdOrderByCreationTimestampAsc(@PathVariable long id) {
        List<Comment> comments = commentService.findCommentsByPostIdOrderByCreationTimestampAsc(id);

        return comments.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }


    /**
     * API to get all post with a request to comment for the current user
     *
     * @return List of Posts the user is requested for a comment
     */
    @GetMapping("/myPosts")
    public List<PostDTO> findPostsByCommentsAuthorID(
            @CookieValue(value = "session", defaultValue = "8484beef-3a5c-49ad-a18b-dde4a39033c9") String sessionToken) {

        // Testuser 1
        long authorID = sessionService.getUserId(sessionToken);

        List<Comment> comments = commentService.findCommentsByAuthor_Id(authorID);

        return comments.stream()
                .map(comment -> postService.findPostById(comment.getPost().getId()))
                .map(PostDTO::new)
                .collect(Collectors.toList());
    }


    /**
     * API to delete a comment by Id
     *
     * @param id Id of comment
     */
    @DeleteMapping("/delete/{id}")
    public void deleteCommentById(@PathVariable long id) {
        commentService.deleteCommentById(id);
    }

    @PutMapping("/updateComment")
    public CommentDTO updateComment(
            @CookieValue(value = "session", defaultValue = "8484beef-3a5c-49ad-a18b-dde4a39033c9") String sessionToken,
            @RequestBody CommentDTO commentDTO) {

        // Testuser 1
        long commentAuthorId = sessionService.getUserId(sessionToken);

        Comment comment = commentService.updateComment(
                commentAuthorId,
                commentDTO.getPostID(),
                commentDTO.getText());

        if (comment == null) {
            return null;
        } else {
            return new CommentDTO(comment);
        }
    }


}
