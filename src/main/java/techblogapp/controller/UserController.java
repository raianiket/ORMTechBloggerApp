package techblogapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import techblogapp.model.User;
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
    public String registration(){
        return "user/registration";
    }

    @RequestMapping(value="/user/login",method = RequestMethod.POST)
    public String loginUser(User user, Model model, HttpSession session){
        if(userService.loginUser(user)){
            model.addAttribute("user",user);
            session.setAttribute("loggeduser",user);
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
