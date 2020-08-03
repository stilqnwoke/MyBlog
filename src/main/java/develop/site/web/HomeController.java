package develop.site.web;



import develop.site.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private final PostService postService;


    public HomeController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("post", postService.getLatestPost());
        return "index";
    }

}