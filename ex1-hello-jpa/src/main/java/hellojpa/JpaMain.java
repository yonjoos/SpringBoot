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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");

            member.setTeam(team);
            team.getMemberList().add(member);

            em.persist(member);


            //########## Member 객체는 그대로, 이름만 바꿔 넣으면 : 영속성에서 Id 그대로
            member.setUsername("member2");
            member.setTeam(team);
            em.persist(member);

            team.getMemberList().add(member);

            //########## Member 객체 새로 생성 : 영속성에서 Id 바뀜
            Member member2 = new Member();
            member.setUsername("member3");
            member.setTeam(team);
            team.getMemberList().add(member2);

            em.persist(member2);


            em.flush();

            Member findMember = em.find(Member.class, member.getId());

            //Team findTeam = findMember.getTeam();
            Team findTeam = em.find(Team.class, team.getId());
            int memberSize = findTeam.getMemberList().size();
            Member member_1 = findTeam.getMemberList().get(0);
            Member member_2 = findTeam.getMemberList().get(1);
            Member member_3 = findTeam.getMemberList().get(2);

            System.out.println("============" + memberSize);
            System.out.println("=============" + member_1);
            System.out.println("=============" + member_2);
            System.out.println("=============" + member_3);


            tx.commit(); //transaction CLOSE

        }catch (Exception e){ // IF ERROR OCCURS
            tx.rollback();
        }finally{ // WHEN THE TASK IS DONE
            em.close(); // DATABASE CONNECTION OFF
        }

        emf.close();

    }
}
