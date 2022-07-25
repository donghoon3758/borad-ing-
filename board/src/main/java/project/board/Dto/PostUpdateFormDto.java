package project.board.Dto;

import lombok.Data;

@Data
public class PostUpdateFormDto {
    private Long id;

    private String title;
    private String content;
}
