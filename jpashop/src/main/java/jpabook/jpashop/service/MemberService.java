package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// readonly = true 옵션을 주면 JPA가 조회하는 곳에서는 성능을 최적화(여기서는 조회가 많으므로 전체 클래스에 옵션을 줌)
@Transactional(readOnly = true) //JPA의 모든 데이터 변경이나 로직은 트랜잭션 안에서 이뤄져야 함
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional  // 조회가 아닌 경우에는 readOnly 넣으면 안되므로 따로 지정
    public Long join(Member member) {
        validateDuplicateMember(member);  //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 전체 회원 목록 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /**
     * 회원 수정 - Api 전용
     */
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
