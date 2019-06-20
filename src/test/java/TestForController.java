import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import techblogapp.controller.HomeController;
import techblogapp.controller.UserController;
import techblogapp.model.Post;
import techblogapp.model.User;
import techblogapp.service.PostService;
import techblogapp.service.UserService;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes={HomeController.class, UserController.class})
public class TestForController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PostService postService;
    @MockBean
    private UserService userService;
    private ArrayList<Post> mockPost=new ArrayList<>();
    @Test
    public void testTheController() throws Exception{
        mockPost.add(new Post("Shubham's POst","SJDLHj",new Date()));
        Mockito.when(postService.getAllPosts()).thenReturn(mockPost);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/");
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response=result.getResponse();
        Assertions.assertEquals("index",response.getForwardedUrl());
    }
    @Test
    public void testTheUserController() throws Exception{
        String username="Aniket";
        String password="Ani@123";
        boolean check=username.equals("Aniket") && password.equals("Ani@123");
        Mockito.when(userService.loginUser(any(User.class))).thenReturn(check);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("" +
                "/user/login").param("username",username).param("password"
        , password);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response=result.getResponse();
        Assertions.assertEquals("/posts",response.getRedirectedUrl());
    }
    @Test
    public void testLoginWithWrongInput() throws Exception{
        String username="Aniket123";
        String password="Ani@123";
        boolean check=username.equals("Aniket") && password.equals("Ani@123");
        Mockito.when(userService.loginUser(any(User.class))).thenReturn(check);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("" +
                "/user/login").param("username",username).param("password"
                , password);
        MvcResult result=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response=result.getResponse();
        Assertions.assertEquals("user/login",response.getForwardedUrl());
    }

}
