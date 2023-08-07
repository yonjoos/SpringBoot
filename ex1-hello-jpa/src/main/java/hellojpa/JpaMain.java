package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin(); //database transaction 시작

        //실제 동작 코드
        Member member = new Member();

        member.setId(1L);
        member.setName("HelloA");

        em.persist(member);
        tx.commit(); //transaction CLOSE

        em.close();

        emf.close();

    }
}
