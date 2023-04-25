package de.academy.backend_pping.innovation_lab.comments;

import de.academy.backend_pping.buddy_core.user.UserDTO;

import java.time.LocalDateTime;

public class CommentDTO {

    private long id;

    private LocalDateTime creationTimeStamp;

    private UserDTO author;

    private long postID;

    private boolean isAnonymous;

    private  String text;


    public CommentDTO() {
    }

    // out
    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.creationTimeStamp = comment.getCreationTimestamp();
        this.author = new UserDTO(comment.getAuthor());
        this.postID = comment.getPost().getId();
        this.isAnonymous = comment.isAnonymous();
        this.text = comment.getText();
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public UserDTO getAuthor() {
        return author;
    }


    public boolean isAnonymous() {
        return isAnonymous;
    }

    public String getText() {
        return text;
    }

    public long getPostID() {
        return postID;
    }

    public void setText(String text) {
        this.text = text;
    }
}
