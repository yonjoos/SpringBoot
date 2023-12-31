package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.repository.MemberRepository;
import study.datajpa.repository.MemberRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberTest {

    @PersistenceContext
    EntityManager em;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testEntity(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamA);
        Member member4 = new Member("member4", 40, teamA);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();


        List<Member> members = em.createQuery(
                "select m from Member m", Member.class)
                .getResultList();

        for(Member m : members){
            System.out.println("member = " + m);
            System.out.println(" -> member.team = " + m.getTeam());
        }

    }

    @Test
    public void JpaEventBaseEntity()throws Exception{
        Member member = new Member("member1");
        memberRepository.save(member); //prePersist 발생
        Thread.sleep(100);
        member.setUsername("member2");

        em.flush(); //preUpdate
        em.clear();

        Member findMember = memberRepository.findById(member.getId()).get();

        System.out.println("findMember.created = " + findMember.getCreatedDate());
        System.out.println("findMember.updated = " + findMember.getLastModifiedDate());
        System.out.println("findMember.createdby = " + findMember.getCreatedBy());
        System.out.println("findMember.lastmodifiedby = " + findMember.getLastModifiedBy());



    }

}