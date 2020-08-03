package develop.site.web;

import develop.site.model.entity.Comment;
import develop.site.model.entity.Post;
import develop.site.model.entity.UserEntity;
import develop.site.service.CommentService;
import develop.site.service.PostService;
import develop.site.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public PostController(CommentService commentService, UserService userService, PostService postService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
    }

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("posts", postService.list());
        return "post/list";
    }

    @RequestMapping("/view/{slug}")
    public String view(@PathVariable(value="slug") String slug, Model model){
        model.addAttribute("post", postService.getBySlug(slug));
        return "post/view";
    }

    @RequestMapping("/byAuthor/{id}")
    public String byAuthor(@PathVariable(value="id") Long id, Model model){
        model.addAttribute("posts", postService.listByAuthor(id));
        return "post/list";
    }

    @RequestMapping(value = "/posts/view/", method = RequestMethod.POST)
    public String postCommentOrReply(Comment comment, Model model){

        model.addAttribute("comment", new Comment());
//
////            commentForSave.setDate("12/18/2015 12:00:00");
////            commentForSave.setUser(user);
//            commentForSave.setComment("ccc");
//            commentForSave.setPost(post);

        commentService.saveComment(comment);
        return "redirect:/posts/view/" + comment.getPost().getSlug();


//        if(comment == null && reply== null) return "redirect:/posts/view/" + id;

//        UserEntity authUser = userService.getAuthenticatedUser();
//        if(authUser == null) return "redirect:/users/login";
//
//
//
//        Post curPost = postService.ge(id);
//
//        if(comment != null) {
//            Comment commentForSave = new Comment();
//            commentForSave.setUser(authUser);
//            commentForSave.setComment(comment);
//            commentForSave.setPost(curPost);
//
//            postService.saveComment(commentForSave);
//        }
//
//        if(reply != null){
//            Reply replyForSave = new Reply();
//            replyForSave.setUser(authUser);
//            replyForSave.setReply(reply);
//            Comment commentForReply = postService.getCommentById(commentId);
//            replyForSave.setComment(commentForReply);
//
//            postService.saveReply(replyForSave);
//        }
//
//        return "redirect:/posts/view/" + id;
    }
}
