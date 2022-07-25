package project.board.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends TimeEntity{
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    //공백 불가능 구현, 유효성 구현 하기 ==> controller에서 @NotEmpty("~~")사용하기
    @Column(nullable = false, length = 15, unique = true)
    private String name;
    @Column(nullable = false, length = 20)
    private String password;
    @Column(nullable = false, length = 30)
    private String nickname;
    @Column(nullable = false, length = 30)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public User(String name, String password,String nickname ,String email) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
    //비밀번호 수정
    public void updateByPassword(String password){
        this.password = password;
    }
    //이메일 수정
    public void updateByEmail(String email) {
        this.email = email;
    }
    //별명 수정
    public void updateByNickname(String nickname) {
        this.nickname=nickname;
    }

    //post 연관관계 메소드
    public void addPost(Post post){
        posts.add(post);
        post.setUser(this);
    }

    public void addComment(Comment comment){
        comments.add(comment);
        comment.setUser(this);
    }
    //생성 메소드
}
