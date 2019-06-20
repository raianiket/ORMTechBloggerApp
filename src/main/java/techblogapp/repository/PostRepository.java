package techblogapp.repository;

import org.springframework.stereotype.Repository;
import techblogapp.model.Post;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class PostRepository {
    @PersistenceUnit(name="blogdb")
    private EntityManagerFactory emf;

    public ArrayList<Post> getAllPost(){
        ArrayList<Post> posts=new ArrayList<Post>();
        EntityManager em=emf.createEntityManager();// SELECT * FROM posts
        TypedQuery<Post> tq = em.createQuery("Select p from Post p", Post.class); //JPQL
        posts=(ArrayList<Post>)tq.getResultList();
        return posts;
        //        try{
//            Class.forName("org.postgresql.Driver"); // register the driver with DriverManager
//            String dbname="blogappdb";
//            String user="postgres";
//            String password="postgres@123";
//            String host="localhost";
//            String port="5432";
//            String url = "jdbc:postgresql://"+host+":"+port+"/"+dbname;
//            // getConnection will match the given url with the registered drivers in DriverManager
//            // And return an object that is responsible for Connecting the application to the database
//            Connection conn=DriverManager.getConnection(url,user,password);
//            // Get a statement object which will execute SQL statements on the database
//            // conn.createStatement() says : Give me an object that application
//            //  can use to execute queries in the db
//
//            Statement stmt=conn.createStatement();
//
//            // Get the result of the query in a ResultSet object
//            ResultSet rs=stmt.executeQuery("SELECT title" +
//                    ",body,post_date FROM posts");
//            while(rs.next()) //rs.next()=if there is a next row --->move to that and return true
//            {
//                Post p=new Post();
//                p.setTitle(rs.getString("title"));
//                p.setBody(rs.getString("body"));
//                p.setDate(rs.getDate("post_date"));
//                posts.add(p);
//            }
//
//        }catch(Exception e){
//            e.printStackTrace();
//        }
    }
    public Post getOnePost(){
        EntityManager em=emf.createEntityManager();
        return em.find(Post.class,3);
    }
    public void createUserPost(Post post, String user){
        EntityManager em=emf.createEntityManager();
        EntityTransaction et=em.getTransaction();
        try{
            et.begin();  // Start a transaction
            em.persist(post); // Process involved in the database
            et.commit(); // Make changes visible to other users..means write the post object and
                            //complete the transaction
        }catch(Exception e){
            et.rollback();
            e.printStackTrace();

        }


    }


}
