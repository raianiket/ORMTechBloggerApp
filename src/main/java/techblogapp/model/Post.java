package techblogapp.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="posts")
public class Post {
    // GeneratedBvalue --> this is a primary key whose value is generated using some strategy
//    AUTO --> Let Hibernate decide(based on dialect which strategy is good
//    SEQUENCE-->A special type of table that contains a next_val column and a number(usually)
//                  The number is automatically updated in the sequence by 1(default)
//                  When the sequence is used
//    IDENTITY --> Uses auto increment(not good for performance but from database point of view)
//    TABLE ----> Old way of storing PKs in a table.....

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String title;
    @Column
    private String body;
    @Column(name="post_date")
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
    @Transient // Use this for objects that you need for application logic and
    // those which are not needed to be stored in the database
    private String springBlog;
    @Transient
    private String javaBlog;
    @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();
    public Post(){

    }
    public Post(String title, String body, Date date) {
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public String getSpringBlog() {
        return springBlog;
    }

    public void setSpringBlog(String springBlog) {
        this.springBlog = springBlog;
    }

    public String getJavaBlog() {
        return javaBlog;
    }

    public void setJavaBlog(String javaBlog) {
        this.javaBlog = javaBlog;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
