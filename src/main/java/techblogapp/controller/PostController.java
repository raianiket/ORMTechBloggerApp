package techblogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import techblogapp.model.Category;
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
    public String getUserPost(HttpSession session,Model model){
        ArrayList<Post> posts=postService.getUserPosts((User)session.getAttribute("loggeduser"));
        model.addAttribute("al_posts",posts);

        return "posts";
    }
    @RequestMapping(value="/post/new",method= RequestMethod.POST)
    public String createPost(){
        return "createpost";
    }
    @RequestMapping(value="/create/post",method= RequestMethod.POST)
    public String createUserPost(Post post,HttpSession session){
        if(post.getJavaBlog()!=null){
            Category category=new Category();
            category.setCategory(post.getJavaBlog());
            post.getCategories().add(category);
        }
        if(post.getSpringBlog()!=null){
            Category category=new Category();
            category.setCategory(post.getSpringBlog());
            post.getCategories().add(category);
        }
        User user=(User)session.getAttribute("loggeduser"); //Object
        post.setUser(user);
        postService.createUserPost(post);

        return "redirect:/posts";
    }
    @RequestMapping("/editPost")
    public String editPage(@RequestParam(name = "postId") Integer postId,Model model){
        Post post=postService.getOnePost(postId);
        if(post!=null) {
            model.addAttribute("post", post);
        }
        else
        {
            model.addAttribute("post",new Post());
        }
        return "post/edit";
    }
    @RequestMapping(value = "/editPost",method = RequestMethod.POST)
    public String editPost(Post updatedPost,HttpSession session){
        User loggeduser=(User) session.getAttribute("loggeduser");
        updatedPost.setUser(loggeduser);
        postService.editPost(updatedPost);
        return "redirect:/posts";
    }
    @RequestMapping("/deletePost")
    public String deletePost(@RequestParam(name = "postId") Integer postId){
        postService.deletePost(postId);
        return "redirect:/posts";
    }

}
