package de.academy.backend_pping.innovation_lab.posts;

import de.academy.backend_pping.buddy_core.user.UserDTO;

import java.time.LocalDateTime;
import java.util.List;

public class PostDTO {

    private long id;
    private LocalDateTime creationTimestamp;

    private int postPoints;

    private String title;

    private String text;

    private UserDTO author;

    private boolean isAnonymous;

    private int numberOfComments;

    private List<UserDTO> commentAuthors;

    public PostDTO() {
    }

    public PostDTO(String title, String text, boolean isAnonymous) {
        this.title = title;
        this.text = text;
        this.isAnonymous = isAnonymous;
    }

    public PostDTO(Post post) {
        this.id = post.getId();
        this.creationTimestamp = post.getCreationTimestamp();
        this.postPoints = post.getPostPoints();
        this.title = post.getTitle();
        this.text = post.getText();
        this.author = new UserDTO(post.getAuthor());// password not filled
        this.isAnonymous = post.isAnonymous();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public int getPostPoints() {
        return postPoints;
    }

    public void setPostPoints(int postPoints) {
        this.postPoints = postPoints;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public int getNumberOfComments() {
        return numberOfComments;
    }

    public void setNumberOfComments(int numberOfComments) {
        this.numberOfComments = numberOfComments;
    }

    public List<UserDTO> getCommentAuthors() {
        return commentAuthors;
    }

    public void setCommentAuthors(List<UserDTO> commentAuthors) {
        this.commentAuthors = commentAuthors;
    }





}
