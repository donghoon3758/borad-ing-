package project.board.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Comment extends TimeEntity{
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(String comment) {
        this.comment = comment;
    }

    public void updateComment(String comment){
        this.comment=comment;
    }

    public void setUser(User user) {
        this.user = user;
        user.getComments().add(this); //양방향 ???!?!?!?!?
    }

    public void setPost(Post post) {
        this.post = post;
        post.getComments().add(this);//양방향 ?!?!?!?!
    }
}
