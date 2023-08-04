package jpabook.jpashop.service;

import jpabook.jpashop.main.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest //메모리랑 이런거 한꺼번에 테스트 할 꺼라서 위에 2개 annotations 꼭 쓰세용
@Transactional //for "RollBack"
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em; // 1) 이렇게 하면 RollBack은 하면서 insert 쿼리도 볼 수 있음

    @Test //Are there any same IDs? in A TRANSACTION
    public void register() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        em.flush(); // 2) 이렇게 하면 RollBack & insert Query 볼 수 있음
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void existing_exception() throws Exception{
        //given
        Member member1 = new Member();
        member1.setUsername("kim1");

        Member member2 = new Member();
        member2.setUsername("kim1");



        //when
        memberService.join(member1);
        try{
            memberService.join(member2);
        }catch(IllegalStateException e){
            return; 
        }

        //then
        Assert.fail("예외가 발생해야 한다");
    }

}