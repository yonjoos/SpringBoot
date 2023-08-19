package jpabook2.jpashop2.service;

import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired MemberService memberService;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long memberId = memberRepository.save(member);

        //then
        Assert.assertEquals(member, memberRepository.findOne(memberId));

    }

    @Test
    public void 중복_회원_예외() throws Exception{
        Member mem1 = new Member();
        Member mem2 = new Member();
        mem1.setName("kim");
        mem2.setName("kim");

        memberService.join(mem1);

        try{
            memberService.join(mem2);
        }catch (IllegalStateException e){
            return;
        }

        Assert.fail("예외 발생해야 함");

    }

}