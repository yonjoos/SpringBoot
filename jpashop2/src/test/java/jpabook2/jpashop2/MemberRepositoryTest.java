package jpabook2.jpashop2;

import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class) //junit한테 스프링으로 테스트 할거야
@SpringBootTest //스프링 부트로 테스트 돌릴거임
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional //test에 있으면 테스트 끝나고 rollback 함
    @Rollback(false) //rollback 하지 마라
    public void testMember() throws Exception{
        //given
        Member member = new Member();
        member.setName("memberA");


        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.findOne(saveId);


        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
        Assertions.assertThat(findMember).isEqualTo(member);

    }

}