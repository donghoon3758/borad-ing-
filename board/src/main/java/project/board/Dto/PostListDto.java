package project.board.Dto;

import project.board.entity.Post;

public class PostListDto {
    private Long id;
    private String writer;
    private String title;
    private Long view;

    public PostListDto(Post post) {
        this.id = post.getId();
        this.writer = post.getWriter();
        this.title = post.getTitle();
        this.view = post.getView();
    }
}
