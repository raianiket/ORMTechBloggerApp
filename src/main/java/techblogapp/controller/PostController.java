package techblogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import techblogapp.model.Post;
import techblogapp.model.User;
import techblogapp.service.PostService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    private PostService postService;


    @RequestMapping("/posts")
    public String getUserPost(User user,Model model){
        ArrayList<Post> posts=postService.getOnePost();
        model.addAttribute("al_posts",posts);

        return "posts";
    }
    @RequestMapping(value="/post/new",method= RequestMethod.POST)
    public String createPost(){
        return "createpost";
    }
    @RequestMapping(value="/create/post",method= RequestMethod.POST)
    public String createUserPost(Post post,HttpSession session){

        User user=(User)session.getAttribute("loggeduser"); //Object
        postService.createUserPost(post,user.getUsername());

        return "redirect:/";
    }
}
