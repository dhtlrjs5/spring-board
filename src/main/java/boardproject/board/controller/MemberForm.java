package boardproject.board.controller;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    @NotNull(message = "회원 이름은 필수입니다.")
    private String name;

    @NotNull(message = "이메일은 필수입니다.")
    private String email;
}
