package project.board.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CommentRequestDto {
    @NotEmpty(message = "댓글내용 입력은 필수입니다.")
    private String comment;
    @NotEmpty(message = "유저 닉네임 입력은 필수입니다.")
    private String nickname;
    @NotEmpty(message = "댓글 남길 글의 제목 입력은 필수입니다.")
    private String title;

    public CommentRequestDto(String nickname, String title,String comment) {
        this.nickname = nickname;
        this.title = title;
        this.comment = comment;
    }
}
