package project.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board.entity.Comment;
import project.board.repository.CommentRepository;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public Long join(Comment comment){
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public void remove(Long id){
        List<Comment> result = commentRepository.findById(id);
        if(result.isEmpty()){
            throw new IllegalStateException("해당 댓글이 존재하지 않습니다.");
        }
        commentRepository.delete(id);
    }

    @Transactional
    public void modifiedComment(Long id, String comment){
        List<Comment> result = commentRepository.findById(id);
        if(result.isEmpty()){
            throw new IllegalStateException("해당 댓글이 존재하지 않습니다.");
        }
        result.get(0).updateComment(comment);
    }

    public List<Comment> findAll(){
        return commentRepository.findAll();
    }
}
