package project.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board.Dto.PostListDto;
import project.board.Dto.PostRequestDto;
import project.board.Dto.PostResponseDto;
import project.board.Dto.PostSearch;
import project.board.entity.Post;
import project.board.repository.PostRepository;

import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Long join(Post post){
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    //타이틀 수정
    public void updatePost(Long id, String title, String content){
        List<Post> findPost = postRepository.findById(id);
        if(findPost.isEmpty()){
            throw new IllegalStateException("해당 게시글이 존재하지 않습니다.");
        }
        findPost.get(0).updateTitle(title);
        findPost.get(0).updateContent(content);
    }
    @Transactional
    public void remove(Long id){
        List<Post> findPost = postRepository.findById(id);
        if(findPost.isEmpty()){
            throw new IllegalStateException("해당 게시글이 존재하지 않습니다.");
        }
        postRepository.delete(id);
    }
    @Transactional
    public void plusView(Long id){
        postRepository.updateView(id);
    }

    public List<Post> findQueryDsl(PostSearch postSearch){
        return postRepository.findQueryDsl(postSearch);
    }
    public List<Post> findAll(){
        return postRepository.findAll();
    }
    public  List<Post> findByTitle(String title){
        return postRepository.findByTitle(title);
    }
    public List<Post> findById(Long id){
        return postRepository.findById(id);
    }
    public List<PostResponseDto> findByIdDto(Long id){
        return postRepository.findByIdDto(id);
    }
}
