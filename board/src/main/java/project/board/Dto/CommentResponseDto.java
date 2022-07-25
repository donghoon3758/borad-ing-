package project.board.Dto;

import lombok.Getter;
import project.board.entity.Comment;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponseDto {
    private Long id;
    private String comment;
    private String nickname;
    private Long postId;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.nickname = comment.getUser().getNickname();
        this.postId = comment.getPost().getId();
    }
}
