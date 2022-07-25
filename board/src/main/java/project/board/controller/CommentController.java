package project.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.board.Dto.CommentRequestDto;
import project.board.entity.Comment;
import project.board.service.CommentService;

import javax.validation.Valid;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comment/new")
    public String commentForm(Model model){
        model.addAttribute("commentRequestDto", new CommentRequestDto());
        return "comment/createComment";
    }

    @PostMapping("/comment/new")
    public String create(@Valid CommentRequestDto commentRequestDto, BindingResult result){
        if(result.hasErrors()){
            return "comment/createComment";
        }
        //타이틀과 닉네임 이용해서 연결 시켜야함
        Comment comment = new Comment(commentRequestDto.getComment());
        commentService.join(comment);
        return "redirect:/";
    }
    //view post html 만들고 거기 버튼을 만들어서 댓글달기 기능을 새로 만들기 혹은
}
