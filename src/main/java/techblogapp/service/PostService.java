package techblogapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techblogapp.model.Post;
import techblogapp.repository.PostRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public ArrayList<Post> getAllPosts(){
            return postRepository.getAllPost();

    }
    public ArrayList<Post> getUserPosts(){
        ArrayList<Post> posts=new ArrayList<Post>();
        Post p2=new Post(); //Dependency
        p2.setTitle("Shubham's Post");
        p2.setBody("This is the body of shuhbam post");
        p2.setDate(new Date());
        posts.add(p2);

        return posts;

    }
    public ArrayList<Post> getOnePost(){
        ArrayList<Post> posts=new ArrayList<Post>();
        posts.add(postRepository.getOnePost());
        return posts;

    }

    public void createUserPost(Post post, String loggeduser) {
        post.setDate(new Date());
        postRepository.createUserPost(post,loggeduser);

    }
}
