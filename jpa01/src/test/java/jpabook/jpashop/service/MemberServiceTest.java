package jpabook.jpashop.service;

import jpabook.jpashop.main.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest //메모리랑 이런거 한꺼번에 테스트 할 꺼라서 위에 2개 annotations 꼭 쓰세용
@Transactional //for "RollBack"
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test //Are there any same IDs? in A TRANSACTION
    @Rollback(value = false)
    public void register() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    public void existing_exception() throws Exception{
        //given

        //when

        //then
    }

}