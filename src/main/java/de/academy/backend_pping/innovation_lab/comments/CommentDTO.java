package de.academy.backend_pping.innovation_lab.comments;

import de.academy.backend_pping.buddy_core.user.UserDTO;
import de.academy.backend_pping.innovation_lab.posts.PostDTO;

import java.time.LocalDateTime;

public class CommentDTO {

    private long id;

    private LocalDateTime creationTimeStamp;

    private final UserDTO author;

    private final PostDTO post;

    private final boolean isAnonymous;

    private final String text;

    // in
    public CommentDTO(UserDTO author, PostDTO post, boolean isAnonymous, String text) {
        this.author = author;
        this.post = post;
        this.isAnonymous = isAnonymous;
        this.text = text;
    }

    // out
    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.creationTimeStamp = comment.getCreationTimestamp();
        this.author = new UserDTO(comment.getAuthor());
        this.post = new PostDTO(comment.getPost());
        this.isAnonymous = comment.isAnonymous();
        this.text = comment.getText();
    }
}
