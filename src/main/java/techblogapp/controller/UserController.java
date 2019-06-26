package techblogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import techblogapp.model.User;
import techblogapp.model.UserProfile;
import techblogapp.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/login") //Method is by default GET
    public String login(HttpSession session){
        if(session.getAttribute("loggeduser")!=null){
            return "redirect:/posts";
        }
        return "user/login";
    }


    @RequestMapping(value="/user/registration")
    public String registration(Model model){
        // User  ->> (null,null,null,null)
        User user=new User();
        UserProfile profile=new UserProfile();
        user.setProfile(profile);
        // User -->> (0,null,null,UserProfile->(null,null,null)
        model.addAttribute("User",user);
        return "user/registration";
    }
    @RequestMapping(value="/user/registration",method = RequestMethod.POST)
    public String registerUser(User user){
        userService.registerUser(user);
        return "redirect:/user/login";
    }

    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    public String loginUser(User user, Model model, HttpSession session){
        //user.getProfile()-->null
        User existingUser=userService.loginUser(user);
        if(existingUser!=null){
            model.addAttribute("user",existingUser);
            session.setAttribute("loggeduser",existingUser);
            return "redirect:/posts";
        }
        else
        {   return "user/login";
        }
    }
    @RequestMapping(value="/users/logout",method=RequestMethod.POST)
    public String logoutUser(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
