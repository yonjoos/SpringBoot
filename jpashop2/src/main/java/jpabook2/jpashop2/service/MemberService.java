package jpabook2.jpashop2.service;


import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true) //데이터 변경하려면 Transactional 안에서 이루어져야해서 추가하기
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional // 얘는 데이터 변경하겠다
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("Member already Exists");
        }
    }


    public List<Member> findAll(){
        return memberRepository.findAll();
    }


    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }

}
