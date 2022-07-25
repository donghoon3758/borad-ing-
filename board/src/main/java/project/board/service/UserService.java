package project.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board.entity.User;
import project.board.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager em;

    @Transactional
    public Long join(User user){
        duplicateCheckUser(user);
        userRepository.save(user);
        return user.getId();
    }

    private void duplicateCheckUser(User user) {
        List<User> result = userRepository.findByName(user.getName());
        if(result == user){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    private void duplicateCheckNickname(String nickname) {
        List<User> result = userRepository.findByNickname(nickname);
        if(!result.isEmpty()){
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }
    private void duplicateCheckEmail(String email) {
        List<User> result = userRepository.findByEmail(email);
        if(!result.isEmpty()){
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }
    private void duplicateCheckUsername(User user) {
        List<User> result = userRepository.findByNickname(user.getNickname());
        if(!result.isEmpty()){
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }
    private void duplicateCheckEmail(User user) {
        List<User> result = userRepository.findByEmail(user.getName());
        if(!result.isEmpty()){
            throw new IllegalStateException("이미 등록된 이메일입니다.");
        }
    }
    @Transactional
    //회원 정보 수정(비밀번호,이메일,별명)
    public void modifiedPassword(Long id, String password){
        List<User> findUser = userRepository.findById(id);
        if(!findUser.isEmpty()){
            throw new IllegalStateException("해당 유저가 존재하지 않습니다.");
        }
        findUser.get(0).updateByPassword(password);
    }
    @Transactional
    public void modifiedEmail(Long id, String email){
        List<User> findUser = userRepository.findById(id);
        if(!findUser.isEmpty()){
            throw new IllegalStateException("해당 유저가 존재하지 않습니다.");
        }
        findUser.get(0).updateByEmail(email);
    }
    @Transactional
    public void modifiedNickname(Long id, String nickname){
        List<User> findUser = userRepository.findById(id);
        if(!findUser.isEmpty()){
            throw new IllegalStateException("해당 유저가 존재하지 않습니다.");
        }
        findUser.get(0).updateByNickname(nickname);
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public List<User> findOne(long id){
        return userRepository.findById(id);
    }
}
