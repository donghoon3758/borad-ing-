package project.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.board.Dto.UserRequestDto;
import project.board.entity.User;
import project.board.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/new")
    public String createForm(Model model){
        model.addAttribute("userRequestDto",new UserRequestDto());
        return "user/createUser";
    }

    @PostMapping("/user/new")
    public String create(@Valid UserRequestDto userRequestDto, BindingResult result){
        if(result.hasErrors()){
            return "user/createUser";
        }
        User user = new User(userRequestDto.getName(), userRequestDto.getPassword(), userRequestDto.getNickname(), userRequestDto.getEmail());
        userService.join(user);
        return "redirect:/";
    }
}
