package de.academy.backend_pping.innovation_lab.posts;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAll();
    List<Post> findPostsByAuthorIdOrderByCreationTimestampDesc(long id);
    Optional<Post> findPostById(long id);

}
