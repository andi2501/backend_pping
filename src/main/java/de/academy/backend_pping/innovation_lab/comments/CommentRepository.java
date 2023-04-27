package de.academy.backend_pping.innovation_lab.comments;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findCommentsByAuthor_Id(long id);

    List<Comment> findCommentsByPostIdOrderByCreationTimestampAsc(long id);


    Optional<Comment> findCommentByAuthor_IdAndPost_Id(long author_id,long post_id);
}
