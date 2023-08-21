package study.datajpa.repository;

import lombok.RequiredArgsConstructor;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import java.util.List;


//이름은 반드시 ***Impl 으로 적어야함, 그래야 스프링jpa가 알아봄
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final EntityManager em;

    @Override
    public List<Member> findMemberCustom(){
        return em.createQuery("select m from Member m").getResultList();
    }
}
