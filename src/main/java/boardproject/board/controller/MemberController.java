package boardproject.board.controller;

import boardproject.board.domain.Member;
import boardproject.board.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/memberCreate";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "members/memberCreate";
        }

        Member member = new Member();
        member.createMember(memberForm.getName(), memberForm.getEmail());

        try {
            memberService.register(member);
        } catch (IllegalStateException e) {
            // 중복 이메일 예외를 BindingResult 필드 에러로 등록
            bindingResult.rejectValue("email", "duplicate", "이미 존재하는 이메일입니다.");
            return "members/memberCreate";
        }

        return "redirect:/members";
    }


    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    @PostMapping("/members")
    public String update(@Valid Member member, BindingResult bindingResult) {
        return "";
    }
}
