package de.academy.backend_pping.innovation_lab.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;


    public PostService() {
    }

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public List<Post> findPostsByAuthorIdOrderByCreationTimestampDesc(long id){
        return postRepository.findPostsByAuthorIdOrderByCreationTimestampDesc(id);
    }

    /**
     * Returns a Post Object based on the post id.
     * <br>
     * If no Post object is found null is returned
     *
     * @param id: from Database generated with saving via createPost(Post post)
     * @return Post OR null
     */
    public Post findPostById(long id){
        return postRepository.findPostById(id)
                .orElse(null) ;
    }


    public void deletePostById(long id){
        postRepository.deleteById(id);
    }

}
