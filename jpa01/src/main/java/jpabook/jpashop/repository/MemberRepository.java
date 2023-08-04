package jpabook.jpashop.repository;


import jpabook.jpashop.main.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //spring bean으로 등록, component scan의 대상
public class MemberRepository {
    @PersistenceContext //이게 있으면 jpa의 entity manager....뭐라는겨
    private EntityManager em;

    public void save(Member member) {
        em.persist(member); //??
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();// opt + cmd + N(iN line)

        //jpql은 sql이랑 조금 다르다고??
        //차이 : sql은 Table 을 대상으로 하는데 얘는 Entity 객체를 대상으로 쿼리를 한다고...(Member인 m 을 대상으로 m entity를 조회해)
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.username = :name", Member.class).setParameter("name", name).getResultList();
    }//강의에선 m.name이라고 했는데 Member class 가보면 name이 아니라 username이라 되어있음
}



