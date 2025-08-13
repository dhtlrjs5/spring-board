package boardproject.board.controller;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostForm {

    private Long memberId;

    //@NotNull(message = "제목은 필수입니다.")
    private String title;

    //@NotNull(message = "글 내용은 필수입니다.")
    private String content;
}
