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
        return member.getId(); // !에러 : id가 Long인데 String으로 주구장창 리턴 하려 했으니 에러 남
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }
}
