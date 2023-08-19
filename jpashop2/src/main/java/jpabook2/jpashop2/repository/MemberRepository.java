package jpabook2.jpashop2.repository;


import jpabook2.jpashop2.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class  MemberRepository {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public MemberRepository(EntityManager em){
        this.em = em;
    }

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }


    /*
    ######## 조회
     */
    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :Name", Member.class)
                .setParameter("Name", name) //아하 파라미터가 저거구나
                .getResultList();
    }
}
