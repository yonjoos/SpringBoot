package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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


            Team team2 = new Team();
            team2.setName("TeamB");
            em.persist(team2);


            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team);
            //team.getMemberList().add(member);
            em.persist(member);


            //########## Member 객체 새로 생성 : 영속성에서 Id 바뀜
            Member member2 = new Member();
            member2.setUsername("member3");
            member2.setTeam(team);
            //team.getMemberList().add(member2);
            em.persist(member2);



            Team findTeam1 = em.find(Team.class, team.getId());
            Team findTeam2 = em.find(Team.class, team2.getId());
            List<Member> members = findTeam1.getMemberList();
            List<Member> members2 = findTeam2.getMemberList();

            System.out.println("######## BEFORE FLUSH ##########");
            System.out.println("######## BEFORE FLUSH ##########");
            System.out.println("######## BEFORE FLUSH ##########");

            System.out.println("member 1의 이름 " + member.getUsername());
            System.out.println("member 2의 이름 " + member2.getUsername());
            System.out.println("member 1의 팀 " + member.getTeam().getName());
            System.out.println("member 2의 팀 " + member2.getTeam().getName());
            System.out.println("Team 1의 목록 " + team.getMemberList());
            System.out.println("Team 2의 목록 " + team2.getMemberList());
            System.out.println("Members 1의 목록 " + members);
            System.out.println("Members 2의 목록 " + members2);


            member2.setTeam(team2);

            em.flush();
            em.clear();

            findTeam1 = em.find(Team.class, team.getId());
            findTeam2 = em.find(Team.class, team2.getId());
            members = findTeam1.getMemberList(); //다시 찾음
            members2 = findTeam2.getMemberList(); //다시 찾음
            List<Member> members3 = findTeam1.getMemberList();
            List<Member> members4 = findTeam2.getMemberList();

            System.out.println("######## AFTER FLUSH ##########");
            System.out.println("######## AFTER FLUSH ##########");
            System.out.println("######## AFTER FLUSH ##########");

            System.out.println("member 1의 이름 " + member.getUsername());
            System.out.println("member 2의 이름 " + member2.getUsername());
            System.out.println("member 1의 팀 " + member.getTeam().getName());
            System.out.println("member 2의 팀 " + member2.getTeam().getName());
            System.out.println("(객체)Team 1의 목록 " + team.getMemberList()); //empty
            System.out.println("(객체)Team 2의 목록 " + team2.getMemberList()); //empty
            System.out.println("Members 1의 목록 " + members); //empty
            System.out.println("Members 2의 목록 " + members2);  //empty
            System.out.println("Members 1(2)의 목록 " + members3); //다시 찾음
            System.out.println("Members 2(2)의 목록 " + members4); //다시 찾음



            //결론, flush를 해도 DB값을 객체에 담으려면 em.find() 해줘야한다.



            tx.commit(); //transaction CLOSE



            Team findTeam3 = em.find(Team.class, team.getId());
            Team findTeam4 = em.find(Team.class, team2.getId());
            List<Member> members5 = findTeam3.getMemberList();
            List<Member> members6 = findTeam4.getMemberList();


            System.out.println("######## AFTER TX ##########");
            System.out.println("######## AFTER TX ##########");
            System.out.println("######## AFTER TX ##########");

            System.out.println("member 1의 이름 " + member.getUsername());
            System.out.println("member 2의 이름 " + member2.getUsername());
            System.out.println("member 1의 팀 " + member.getTeam().getName());
            System.out.println("member 2의 팀 " + member2.getTeam().getName());
            System.out.println("(객체)Team 1의 목록 " + team.getMemberList());
            System.out.println("(객체)Team 2의 목록 " + team2.getMemberList());
            System.out.println("Members 1의 목록 " + members);
            System.out.println("Members 2의 목록 " + members2);
            System.out.println("Members 1(2)의 목록 " + members5); //다시 안 찾음
            System.out.println("Members 2(2)의 목록 " + members6); //다시 안 찾음
        }catch (Exception e){ // IF ERROR OCCURS
            tx.rollback();
        }finally{ // WHEN THE TASK IS DONE
            em.close(); // DATABASE CONNECTION OFF

        }

        emf.close();

    }
}
