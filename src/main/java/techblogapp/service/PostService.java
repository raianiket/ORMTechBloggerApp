package techblogapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techblogapp.model.Post;
import techblogapp.model.User;
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

    public Post getOnePost(Integer postId){
        Post post=postRepository.getOnePost(postId);
        return post;

    }
    public ArrayList<Post> getUserPosts(User loggeduser){
        ArrayList<Post> posts=postRepository.getUserPost(loggeduser);
        return posts;

    }

    public void createUserPost(Post post) {
        post.setDate(new Date());
        postRepository.createUserPost(post);

    }


    public void editPost(Post updatedPost) {
        postRepository.editPost(updatedPost);

    }

    public void deletePost(Integer postId) {
        postRepository.deletePost(postId);

    }
}
