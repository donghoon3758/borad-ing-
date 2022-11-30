package project.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.board.Dto.CommentRequestDto;
import project.board.entity.Comment;
import project.board.entity.Post;
import project.board.entity.User;
import project.board.service.CommentService;
import project.board.service.PostService;
import project.board.service.UserService;

import javax.validation.Valid;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/comment/new")
    public String create(@RequestParam("id") Long id, @RequestParam("content") String content) throws Exception{
        CommentRequestDto commentRequestDto = new CommentRequestDto();
        commentRequestDto.setContent(content);
        Post post = postService.findById(id).get(0);
        //User user = userService.findOne(id).get(0);

        Comment comment = new Comment(commentRequestDto.getContent());
        //user.addComment(comment);
        post.setComment(comment);
        comment.setPost(post);
        //comment.setUser(user);
        commentService.join(comment);
        return "redirect:/";
    }

    //view post html 만들고 거기 버튼을 만들어서 댓글달기 기능을 새로 만들기 혹은
}
