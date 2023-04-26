package de.academy.backend_pping.innovation_lab.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/comments")
public class CommentsController {

    private CommentService commentService;


    public CommentsController() {
    }

    @Autowired
    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
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

}
