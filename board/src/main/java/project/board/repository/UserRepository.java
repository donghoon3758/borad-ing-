package project.board.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.board.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user){
        em.persist(user);
    }

    public List<User> findAll(){
        return em.createQuery("select u from User u",User.class)
                .getResultList();
    }

    public List<User> findById(Long id){
        return em.createQuery("select u from User u where u.id = :id", User.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<User> findByName(String name){
        return em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
    }
    public List<User> findByNickname(String nickname){
        return em.createQuery("select u from User u where u.nickname = :nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();
    }
    public List<User> findByEmail(String email){
        return em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
    }

}
