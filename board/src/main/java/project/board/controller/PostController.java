package project.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.board.Dto.PostListDto;
import project.board.Dto.PostRequestDto;
import project.board.Dto.PostSearch;
import project.board.Dto.PostUpdateFormDto;
import project.board.entity.Post;
import project.board.service.PostService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/post/new")
    public  String createForm(Model model){
        model.addAttribute("postRequestDto",new PostRequestDto());
        return "post/createPost";
    }

    @PostMapping("/post/new")
    public String write(@Valid PostRequestDto postDto , BindingResult result){
        if(result.hasErrors()){
            return "post/createPost";
        }
        Post post = new Post(postDto.getWriter(), postDto.getTitle(), postDto.getContent());
        postService.join(post);
        return "redirect:/";
    }

    @GetMapping("/post/list")
    public String list(@ModelAttribute("postSearch")PostSearch postSearch, Model model){
        List<Post> posts = postService.findQueryDsl(postSearch);
        model.addAttribute("posts",posts);
        return "post/postList";
    }

    @PostMapping("/list/{postId}/edit")
    public String update(@ModelAttribute("form")PostUpdateFormDto form){
        postService.updatePost(form.getId(),form.getTitle(),form.getContent());
        return "redirect:/list";
    }

    @PostMapping(value = "/list/{postId}/cancel")
    public String cancelOrder(@PathVariable("postId") Long postId) {
        postService.remove(postId);
        return "redirect:/orders";
    }
}
