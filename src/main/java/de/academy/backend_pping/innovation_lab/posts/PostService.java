package de.academy.backend_pping.innovation_lab.posts;

import de.academy.backend_pping.buddy_core.user.UserDTO;
import de.academy.backend_pping.buddy_core.user.UserEntity;
import de.academy.backend_pping.buddy_core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private PostRepository postRepository;

    private UserService userService;


    public PostService() {
    }

    @Autowired
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    /**
     * This service saves a new post in database and generates a
     * PostDTO for returning to requester including List of authors for comment request.
     *
     * @param userId Author
     * @param title  Title of Post
     * @param text   Text of Post
     * @return PostDTO to be send back to requester
     */
    public PostDTO createPostAndReturn(long userId, String title, String text, int numberOfComments) {

        UserEntity author = userService.findById(userId);
        if (author == null) {
            return null;
        }

        // generate Post Entity based on input
        Post post = new Post(author, title, text);
        post = createPost(post);

        // generate PostDTO based on new post
        PostDTO returnPost = new PostDTO(post);

        // get List of random comment authors

        return addListOfCommentAuthors(returnPost, numberOfComments);
    }

    private PostDTO addListOfCommentAuthors(PostDTO returnPost, int numberOfComments) {

        // Get all User
        List<UserEntity> userEntities = userService.findAll();
        // shuffel List of UserEntities
        Collections.shuffle(userEntities);

        // Get maximum possible number of authors (-1 because post author should not be comment author)
        int numberOfMaxAuthors = Math.min(numberOfComments, userEntities.size() - 1);

        // get sublist of shuffeld userlist; index 0, numberOfMaxAuthors+1 (in case of post author is part of list)
        userEntities = userEntities.subList(0, numberOfMaxAuthors + 1);

        // generate filtered outputlist
        List<UserDTO> commentAuthors = userEntities.stream()
                .filter(userEntity -> userEntity.getId() != returnPost.getAuthor().getId())
                .map(UserDTO::new)
                .collect(Collectors.toList());

        // remove last author in case of author was not part of filtered list to match numberOfMaxAuthors request
        commentAuthors = commentAuthors.subList(0, numberOfMaxAuthors);

        // add comment author list to returnPost and update numberOfMaxAuthors
        returnPost.setCommentAuthors(commentAuthors);
        returnPost.setNumberOfComments(numberOfMaxAuthors);

        return returnPost;
    }


    public List<Post> findPostsByAuthorIdOrderByCreationTimestampDesc(long id) {
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
    public Post findPostById(long id) {
        return postRepository.findPostById(id)
                .orElse(null);
    }


    public void deletePostById(long id) {
        postRepository.deleteById(id);
    }

}
