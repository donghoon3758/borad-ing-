package project.board.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import project.board.Dto.PostResponseDto;
import project.board.Dto.PostSearch;
import project.board.entity.Post;
import project.board.entity.QPost;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static project.board.entity.QPost.post;

@Repository
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostRepository {
    @PersistenceContext
    private EntityManager em;

    JPAQueryFactory query = new JPAQueryFactory(em);


    public void updateView(Long id){
        em.createQuery("update p from Post p set p.view = p.view + 1 where p.id = :id")
                .setParameter("id",id);
    }
    //글작성
    public void save(Post post){
        em.persist(post);
    }
    //글삭제
    public void delete(Long id){
        em.createQuery("delete p from Post p where p.id = :id");
    }

    public List<Post> findAll(){
        return em.createQuery("select p from Post p",Post.class)
                .getResultList();
    }
    //동적쿼리
    public List<Post> findQueryDsl(PostSearch postSearch){
        return query
                .selectFrom(post)
                .where(titleEq(postSearch.getTitle()))
                .fetch();
    }
    private BooleanExpression titleEq(String title) {
        if(title == null){
            return null;
        }
        return post.title.eq(title);
    }

    public List<Post> findByTitle(String title){
        return em.createQuery("select p from Post p where p.title = :title", Post.class)
                .setParameter("title",title)
                .getResultList();
    }

    public List<Post> findById(Long id) {
        return em.createQuery("select p from Post p where p.id = :id",Post.class)
                .setParameter("id",id)
                .getResultList();
    }

    public List<PostResponseDto> findByIdDto(Long id) {
        return em.createQuery("select p from Post p where p.id = :id", PostResponseDto.class)
                .setParameter("id",id)
                .getResultList();
    }
}
