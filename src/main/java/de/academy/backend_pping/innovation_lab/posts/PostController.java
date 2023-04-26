package de.academy.backend_pping.innovation_lab.posts;

import de.academy.backend_pping.buddy_core.user.UserEntity;
import de.academy.backend_pping.buddy_core.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/posts")
public class PostController {


    private PostService postsService;
    private UserService userService;

    public PostController() {
    }

    @Autowired
    public PostController(PostService postsService, UserService userService) {
        this.postsService = postsService;
        this.userService = userService;
    }

    /**
     * A new post is provided via PostMapping.<br>
     * If the post can be saved in Database the saved entity is returned, including id for future references. <br>
     * If the provided entity could not be saved a newly generated Post is returned with a message from the Master of Errors.<br>
     *
     * @param postDTO: Post to be created via PostMapping
     * @return Post entity including id OR Error Post :)
     */
    @PostMapping("/create")
    public @ResponseBody PostDTO createPost(
            @CookieValue(value = "userID", defaultValue = "1") long id,
            @RequestBody PostDTO postDTO) {

        // UserEntity can be extracted from Session Information in a later step
        UserEntity author = userService.findById(id);
        if (author == null) {
            return new PostDTO("3rr0r M4ster", "Post could not be saved in database", false);
        }

        // generate Post Entity based on input
        Post post = new Post(
                author,
                postDTO.getTitle(),
                postDTO.getText());

        post = postsService.createPost(post);
        return new PostDTO(post);

    }


    /**
     * Returns a Post Object based on the post id.
     * <br>
     * If no Post object is found null is returned
     *
     * @param id: Id of Post for finding
     * @return Post OR null
     */
    @GetMapping("/find/{id}")
    public @ResponseBody PostDTO findPostById(@PathVariable long id){
        Post post =  postsService.findPostById(id);

        if (post==null){
            return null;
        } else {
            return new PostDTO(post);
        }
    }

    /**
     * Delete a Post Object based on the id.<br>
     * If no object is found in Database it is silently ignored,
     * hence no return value for success.
     *
     * @param id Id of Post to delete
     */
    @DeleteMapping("/delete/{id}")
    public void deletePostById(@PathVariable long id){
        postsService.deletePostById(id);
    }

    /**
     * Returns a List of Post Objects based on the author id.<br>
     * The order of posts inside the List is based on the creation timestamp
     * in descending order.<br>
     * If no object or author is found an empty list is returned
     *
     * @param id: id of author
     * @return List of Posts OR empty List
     */
    @GetMapping("/author/{id}")
    public List<PostDTO> findPostsByAuthorIdOrderByCreationTimestampDesc(@PathVariable long id){
        List<Post> posts = postsService.findPostsByAuthorIdOrderByCreationTimestampDesc(id);

        return posts.stream()
                .map(post -> new PostDTO(post))
                .collect(Collectors.toList());

    }

}