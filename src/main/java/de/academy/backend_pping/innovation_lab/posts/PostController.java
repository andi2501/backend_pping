package de.academy.backend_pping.innovation_lab.posts;

import de.academy.backend_pping.buddy_core.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/posts")
public class PostController {


    private PostService postsService;

    public PostController() {
    }

    @Autowired
    public PostController(PostService postsService) {
        this.postsService = postsService;
    }


    /**
     * A new post is provided via PostMapping.<br>
     * If the post can be saved in Database the saved entity is returned, including id for future references. <br>
     * If the provided entity could not be saved a newly generated Post is returned with a message from the Master of Errors.<br>
     *
     * @param post: Post to be created via PostMapping
     * @return Post entity including id OR Error Post :)
     */
    @PostMapping("/create")
    public @ResponseBody Post createPost(@RequestBody Post post){
        try {
            return postsService.createPost(post);
        }
        catch (IllegalArgumentException iAE){
            return new Post(new UserEntity("ErrorMaster",""),"IllegalArgumentException",
                    "The provided entity could not be saved due to IllegalArgumentException");

        }
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
    public @ResponseBody Post findPostById(@PathVariable long id){
        return postsService.findPostById(id);
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
    public List<Post> findPostsByAuthorIdOrderByCreationTimestampDesc(@PathVariable long id){
        return postsService.findPostsByAuthorIdOrderByCreationTimestampDesc(id);
    }





}
