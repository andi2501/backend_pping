package de.academy.backend_pping.innovation_lab.posts;


import de.academy.backend_pping.buddy_core.user.UserEntity;
import de.academy.backend_pping.innovation_lab.comments.Comment;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private long id;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;
    @ManyToOne
    private UserEntity author;
    private int postPoints;

    private String title;

    private String text;

    private boolean isAnonymous;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post() {
    }


    public Post(UserEntity author, String title, String text) {
        this.author = author;
        this.title = title;
        this.text = text;

        this.isAnonymous = author.isAnonymous();
        this.postPoints = 0;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
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

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public List<Comment> getComments() {
        return comments;
    }


    /*
    Timestamp not included in equals and hashcode due to different accuracy of
    produced timestamp by @CreationTimestamp
    and returned timestamp by postService.findPostById
     */





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return id == post.id && postPoints == post.postPoints && isAnonymous == post.isAnonymous && Objects.equals(author, post.author) && Objects.equals(title, post.title) && Objects.equals(text, post.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, postPoints, title, text, isAnonymous);
    }
}
