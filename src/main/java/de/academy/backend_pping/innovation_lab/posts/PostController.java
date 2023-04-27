package de.academy.backend_pping.innovation_lab.posts;

import de.academy.backend_pping.buddy_core.session.SessionService;
import de.academy.backend_pping.innovation_lab.comments.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/posts")
public class PostController {


    private PostService postsService;
    private CommentService commentService;

    private SessionService sessionService;

    public PostController() {
    }

    @Autowired
    public PostController(PostService postsService, CommentService commentService, SessionService sessionService) {
        this.postsService = postsService;
        this.commentService = commentService;
        this.sessionService = sessionService;
    }

    /**
     * A new post is provided via PostMapping.<br>
     * If the post can be saved in Database the saved entity is returned, including id for future references. <br>
     * If the provided entity could not be saved a newly generated Post is returned with a message from the Master of Errors.<br>
     *
     * @param postDTO: Post to be created via PostMapping
     * @return Post including List of users for comments
     */
    @PostMapping("/create")
    public @ResponseBody PostDTO createPost(
            @CookieValue(value = "session", defaultValue = "8484beef-3a5c-49ad-a18b-dde4a39033c9") String sessionToken,
            @RequestBody PostDTO postDTO) {

        long authorID = sessionService.getUserId(sessionToken);

        PostDTO returnPost = postsService.createPostAndReturn(
                authorID,
                postDTO.getTitle(),
                postDTO.getText(),
                postDTO.getNumberOfComments());


        // save blanko comment in database for each commentAuthor
        commentService.saveDefaultCommentsByCommentAuthorsInPostDTO(returnPost);

        return returnPost;

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
    public @ResponseBody PostDTO findPostById(@PathVariable long id) {
        Post post = postsService.findPostById(id);

        if (post == null) {
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
    public void deletePostById(@PathVariable long id) {
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
    public List<PostDTO> findPostsByAuthorIdOrderByCreationTimestampDesc(@PathVariable long id) {
        List<Post> posts = postsService.findPostsByAuthorIdOrderByCreationTimestampDesc(id);

        return posts.stream()
                .map(post -> new PostDTO(post))
                .collect(Collectors.toList());

    }

}
