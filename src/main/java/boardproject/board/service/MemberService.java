package boardproject.board.service;

import boardproject.board.domain.Member;
import boardproject.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long register(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findByEmailMembers = memberRepository.findByEmail(member.getEmail());

        if (!findByEmailMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    /**
     * 회원 한명 조회
     */
    public Member findById(Long id) {
        return memberRepository.findById(id);
    }
}