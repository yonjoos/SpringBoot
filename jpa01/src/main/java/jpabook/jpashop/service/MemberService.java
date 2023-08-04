package jpabook.jpashop.service;

import jpabook.jpashop.main.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service //component
@Transactional(readOnly = true)//Transaction 안에서 해결되어야한다 , public들은 기본적으로 transactional 안에 다 걸려들어갑니다
//@AllArgsConstructor
@RequiredArgsConstructor
public class MemberService {

    //@RequiredArgsConstructor 쓰면, final 붙은 것만 생성자 만들어줌
    private final MemberRepository memberRepository;



    //핵심기능 1. 회원 가입
    @Transactional(readOnly = false) //쓰기에는 readOnly true 하면 변경이 안 돼
    public Long join(Member member){
        validateDuplicateMember(member); //문제가 있으면 터트려
        memberRepository.save(member); // !에러 : java가 제공하는 Member라는 class가 있나봄
        return member.getId(); //아니...왜 게터 일 안 함?
    }

    private void validateDuplicateMember(Member member){
        //Exception
        //중복회원 검증
        List<Member> findMembers = memberRepository.findByName(member.getUsername());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("Member Already Exists");
        }
    }

    //핵심기능 2. 회원 전체 조회
    //@Transactional(readOnly = true) //jpa가 조회하는 것에서는 좀 더 최적화~~ 뭔 소린지 모름, 읽기에는 가급적 readOnly = true 넣어주세요
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    //@Transactional(readOnly = true) //jpa가 조회하는 것에서는 좀 더 최적화~~ 뭔 소린지 모름
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
