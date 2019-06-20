package techblogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import techblogapp.model.Post;
import techblogapp.service.PostService;

import java.util.ArrayList;
import java.util.Date;
//<!--7668514258-->
@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @RequestMapping("/")
    public String getAllPosts(Model model){
        ArrayList<Post> posts=postService.getAllPosts();
        model.addAttribute("al_posts",posts);
        return "index";
    }


}

