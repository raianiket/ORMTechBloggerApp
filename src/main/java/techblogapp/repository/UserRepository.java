package techblogapp.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import techblogapp.model.User;

import javax.persistence.*;

@Repository
public class UserRepository {
    @PersistenceUnit(name="blogdb")
    private EntityManagerFactory emf;

    public void registerUser(User user) {
        EntityManager em=emf.createEntityManager();
        EntityTransaction et=em.getTransaction();
        try{
            et.begin();
            em.persist(user);
            et.commit();
        }catch (Exception e){
            et.rollback();
            e.printStackTrace();

        }
    }

    public User checkUser(User user) {
        EntityManager em=emf.createEntityManager();
        TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.username=:username AND u.password=:password", User.class);
        tq.setParameter("username",user.getUsername());
        tq.setParameter("password",user.getPassword());
        try{
            User u=tq.getSingleResult();
            return u;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
