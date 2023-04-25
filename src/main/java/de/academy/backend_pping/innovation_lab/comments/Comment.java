package de.academy.backend_pping.innovation_lab.comments;


import de.academy.backend_pping.buddy_core.user.UserEntity;
import de.academy.backend_pping.innovation_lab.posts.Post;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private long id;


    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private Post post;

    private String text;

    private boolean isAnonymous;


    public Comment() {
    }

    public Comment(UserEntity author, Post post, String text) {
        this.author = author;
        this.post = post;

        this.text = text;
        this.isAnonymous = author.isAnonymous();
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

    public Post getPost() {
        return post;
    }

    public String getText() {
        return text;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }


    // CreationTimeStamp removed from equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return id == comment.id && isAnonymous == comment.isAnonymous && Objects.equals(author, comment.author) && Objects.equals(post, comment.post) && Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, post, text, isAnonymous);
    }
}
