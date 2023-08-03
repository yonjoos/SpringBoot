package jpabook.jpashop.main;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Getter @Setter
public class MemberRepository { //바꾼거 2 : 이거 위치를 main 안으로 옮겨봄
    @PersistenceContext
    EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
