package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null이면 로그인 실패
     */
    public Member login(String loginId, String password) {

        List<Member> findMember = memberRepository.findByLoginId(loginId);
        Member member = findMember.get(0);
        if (member.getPassword().equals(password)) {
            return member;
        } else {
            return null;
        }
    }
}
