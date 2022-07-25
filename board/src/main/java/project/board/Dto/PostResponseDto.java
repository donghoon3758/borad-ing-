package project.board.Dto;

import lombok.Getter;
import project.board.entity.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private Long id;

    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdDate,modifiedDate;
    private Long view;
    private Long userId;
    private List<CommentResponseDto> comments;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
        this.createdDate = post.getCreatedDate();
        this.modifiedDate = post.getLastModifiedDate();
        this.view = post.getView();
        this.userId = post.getUser().getId();
        this.comments = post.getComments().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
