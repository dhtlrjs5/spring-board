package boardproject.board.service;

import boardproject.board.domain.Member;
import boardproject.board.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;

    @Test
    void registerTest() {
        Member member = new Member();
        member.setName("오시건");
        member.setEmail("dhtlrjs@email.com");

        Long findMemberId = memberService.register(member);

        assertThat(member).isEqualTo(memberRepository.findById(findMemberId));
    }
}