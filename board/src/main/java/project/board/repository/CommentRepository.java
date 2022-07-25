package project.board.repository;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import project.board.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRepository {
    @PersistenceContext
    private EntityManager em;
    
    public void save(Comment comment){
        em.persist(comment);
    }
    //삭제시 어떻게 삭지할지는 나중에 보고 결정 지금은 id로 삭제
    public void delete(Long id){
        em.createQuery("delete c from Comment c where c.id = :id")
                .setParameter("id",id);
    }

    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }
    public List<Comment> findById(Long id){
        return em.createQuery("select c from Comment c where c.id = :id",Comment.class)
                .setParameter("id",id)
                .getResultList();
    }
}
