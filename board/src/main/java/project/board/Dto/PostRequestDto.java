package project.board.Dto;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class PostRequestDto {
    @NotEmpty(message = "작성자 입력은 필수입니다.")
    private String writer;

    @NotBlank(message = "제목 입력은 필수입니다.")
    private String title;

    @NotEmpty(message = "내용 입력은 필수입니다.")
    private String content;

    public PostRequestDto(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
