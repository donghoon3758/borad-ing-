package project.board.service;

import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.board.entity.User;
import project.board.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public void 회원가입 (){
        User user1 = new User("zzugak","dlehdgns1@","훈깡패","zzugak@naver.com");

        Long id1 = userService.join(user1);

        Assertions.assertThat(user1.getId()).isEqualTo(id1);
    }
}