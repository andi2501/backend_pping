package de.academy.backend_pping.innovation_lab.comments;

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


    public CommentsController() {
    }

    @Autowired
    public CommentsController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    /**
     * API accepts a comment to be saved in Database.<br>
     * It returns the saved comment to provide an id and timestamp for later use.
     * @param commentDTO DTO to get Data via Requestbody for comments
     * @return saved entity
     */
    @PostMapping("/create")
    public @ResponseBody CommentDTO createComment(
            @CookieValue(value="userID", defaultValue = "11") long authorID,
            @RequestBody CommentDTO commentDTO){

        // TODO: 25.04.2023 replace with current user from session management
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
     * @param id Id of post
     * @return List of Comments
     */
    @GetMapping("/post{id}")
    public List<CommentDTO> findCommentsByPostIdOrderByCreationTimestampAsc(@PathVariable long id){
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
            @CookieValue(value = "userID", defaultValue = "125") long id){

        List<Comment> comments = commentService.findCommentsByAuthor_Id(id);

        return comments.stream()
                .map(comment -> postService.findPostById(comment.getPost().getId()))
                .map(PostDTO::new)
                .collect(Collectors.toList());
    }



    // TODO: 26.04.2023 provide update function to update blanko comments -->
    //  updateComment(current User Id --> comment Author , post Id send )
    //  find comment to update based on comment.getAuthor().getId() = current_User.getID() + post_Id =  ?

    @DeleteMapping("/delete/{id}")
    public void deleteCommentById(@PathVariable long id){
        commentService.deleteCommentById(id);
    }







}
