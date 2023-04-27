package de.academy.backend_pping.innovation_lab.comments;

import de.academy.backend_pping.buddy_core.user.UserEntity;
import de.academy.backend_pping.buddy_core.user.UserService;
import de.academy.backend_pping.innovation_lab.posts.Post;
import de.academy.backend_pping.innovation_lab.posts.PostDTO;
import de.academy.backend_pping.innovation_lab.posts.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostService postService, UserService userService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
    }

    public List<Comment> findCommentsByPostIdOrderByCreationTimestampAsc(long id){

        return commentRepository.findCommentsByPostIdOrderByCreationTimestampAsc(id);
    }

    /**
     * A comment will be saved in database.<br>
     * In case of post or author not found in database null is returned.
     *
     * @param authorId Id of Comment author
     * @param postId Id of linked Post
     * @param text Comment Text
     * @return Comment OR null
     */
    public Comment createComment(long authorId, long postId, String text){

        Post post = postService.findPostById(postId);
        UserEntity author = userService.findById(authorId);

        if (post == null || author == null){
            return null;
        } else {
            return commentRepository.save(new Comment(author, post, text));
        }

    }

    public void deleteCommentById(long id){
        commentRepository.deleteById(id);
    }

    public void saveDefaultCommentsByCommentAuthorsInPostDTO(PostDTO postDTO){

        postDTO.getCommentAuthors().stream()
                        .forEach(userDTO -> createComment(
                                userDTO.getId(),
                                postDTO.getId(),
                                "Bitte schreibe einen Kommentar"));
    }

    public List<Comment> findCommentsByAuthor_Id(long id){
        return commentRepository.findCommentsByAuthor_Id(id);
    }

    public Comment updateComment(long commentAuthorId, long postId, String text){
       // find correct comment

        Comment comment = commentRepository
                .findCommentByAuthor_IdAndPost_Id(commentAuthorId,postId)
                .orElse(null);

        if (comment == null){
            return null;
        } else {
            // update Text of comment
            comment.setText(text);
            //save comment --> works as update if id exists
            return commentRepository.save(comment);
        }
    }



}
