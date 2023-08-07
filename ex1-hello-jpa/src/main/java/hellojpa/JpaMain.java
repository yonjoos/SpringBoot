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

        try{
            //실제 동작 코드
            Member member = new Member();

            member.setId(2L);
            member.setName("HelloB");

            em.persist(member);
            tx.commit(); //transaction CLOSE

        }catch (Exception e){ // IF ERROR OCCURS
            tx.rollback();
        }finally{ // WHEN THE TASK IS DONE
            em.close(); // DATABASE CONNECTION OFF
        }

        emf.close();

    }
}
