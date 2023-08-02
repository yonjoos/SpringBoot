package jpabook.jpashop;

import jpabook.jpashop.domain.item.Member;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    //저장하는 코드
    public Long save(Member member){
        em.persist(member); //member 집어넣음
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }


}
