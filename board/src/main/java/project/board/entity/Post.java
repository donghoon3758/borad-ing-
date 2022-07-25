package project.board.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends TimeEntity{
    @Id @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String writer;

    @Column(length = 500)
    private String title;
    @Column
    private String content;

    @Column(columnDefinition = "long default 0")
    private Long view;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public Post(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
    public void updateTitle(String title){
        this.title=title;
    }
    public void updateContent(String content){
        this.content=content;
    }

    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);//양방향일때 쓰는건데 나중에 수정해야함
    }

    public void setComment(Comment comment){
        comments.add(comment);
        comment.setPost(this);
    }
}
