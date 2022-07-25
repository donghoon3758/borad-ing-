package project.board.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@RequiredArgsConstructor
public class UserRequestDto {
    @NotEmpty(message = "아이디 입력은 필수입니다")
    private String name;
    @NotEmpty(message = "비밀번호 입력은 필수입니다.")
    private String password;
    @NotEmpty(message = "닉네임 입력은 필수입니다.")
    private String nickname;
    @NotEmpty(message = "이메일 입력은 필수입니다.")
    private String email;

    public UserRequestDto(String name, String password, String nickname, String email) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }
}
