package study.datajpa.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public MemberJpaRepository(EntityManager em){
        this.em = em;
    }

    public Member save(Member member){
        em.persist(member);
        return member;
    }

    public Member find(Long id){
        return em.find(Member.class, id);
    }


}
