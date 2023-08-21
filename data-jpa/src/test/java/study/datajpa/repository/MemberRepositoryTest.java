package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void testMember() {

        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        //Optional<Member> byId = memberRepository.findById(savedMember.getId());
        Member findMember = memberRepository.findById(savedMember.getId()).get();

        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basifCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();

        Assertions.assertThat(findMember1).isEqualTo(member1);

        List<Member> all = memberRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);

        long count = memberRepository.count();
        Assertions.assertThat(count).isEqualTo(2);

        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deletedCount = memberRepository.count();
        Assertions.assertThat(deletedCount).isEqualTo(0);


    }


    @Test
    public void findByUserNameAndAgeGreaterThan(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        Assertions.assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        Assertions.assertThat(result.get(0).getAge()).isEqualTo(20);
    }

    @Test
    public void testNamedQuery(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("Bbb", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("AAA");
        Member findMember = result.get(0);
        Assertions.assertThat(findMember).isEqualTo(m1);
    }

    @Test
    public void testQuery(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("Bbb", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("AAA", 10);
        Assertions.assertThat(result.get(0)).isEqualTo(m1);
    }

    @Test
    public void findUsernameList(){
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();
        for(String s : usernameList){
            System.out.println("s = " + s);
        }
    }

    @Test
    public void findMemberDto(){

        Team team = new Team("teamA");
        teamRepository.save(team);


        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for(MemberDto s : memberDto){
            System.out.println("s = " + s);
        }


    }


    @Test
    public void findByNames(){

        Team team = new Team("teamA");
        teamRepository.save(team);


        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<Member> memberDto = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for(Member s : memberDto){
            System.out.println("s = " + s);
        }


    }

    @Test
    public void returnType(){

        Team team = new Team("teamA");
        teamRepository.save(team);


        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<Member> memberDto = memberRepository.findListByUsername("AAA");
        for(Member s : memberDto){
            System.out.println("s = " + s);
        }

        //COLLECTION 의 경우 : 없는 값 찾으면 Empty collection 반환함, null 아님
        List<Member> memberDtos = memberRepository.findListByUsername("AAsfdA");
        for(Member s : memberDtos){
            System.out.println("s = " + s);
        }

        //단건조회 의 경우 : 없는 값 찾으면 Empty collection 반환함
        //JPA는 getSingleResult : Exception, Spring Data JPA는 Exception ㄴㄴ, null 로 내보냄
        Member memberDtoss = memberRepository.findMemberByUsername("AAdfaA");
        System.out.println("s = " + memberDtoss);

        //근데 귀찮으니까 그냥 다 Optional으로 싸
        //근데 얘도 멤버 이름이 중복되면 Exception 터짐:nonUniqueResultException , springframework Exception으로 반환해서 변환해줌
        //repository는 jpa 기술이 될 수도 , MongoDB 기술이 될 수 있음, 하튼 스프링이 뭐라카노
        Optional<Member> findMember = memberRepository.findOptionalByUsername("dsf");
        System.out.println("findMember = " + findMember);



    }


    @Test
    public void paging(){
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        //이거 API에 Entity 그대로 반환하면 안 되고 DTO로 변환해야함
        Page<Member> page = memberRepository.findByAge(age, pageRequest);
        Page<MemberDto> toMao = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));


        List<Member> content = page.getContent();
        long totalElements = page.getTotalElements();

        System.out.println("ㅁㅁㅁㅁㅁㅁㅁ" + page.getNumber());



    }

    @Test
    public void bulkUpdate(){
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));
        //영속성 컨텍스트에만 있고 반영은 안 된 상태인데 bulk Update 해버리면??

        List<Member> result = memberRepository.findByUsername("member5");
        //DB에만 반영되고, 영속성 컨텍스트에는 반영 안 됨.
        //BTW, bulkUpdate function sends Query directly to DB

        //So must do em.flush
        em.flush();
        em.clear(); //make em clear so jpa would reload em
        Member member5 = result.get(0);
        System.out.println("member5 = " + member5);

        int resultCount = memberRepository.bulkAgePlus(20);

        Assertions.assertThat(resultCount).isEqualTo(3);

    }

    @Test
    public void findMemberLazy(){
        //given
        //mem1 은 teamA 참조
        //mem2, teamB

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);

        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findAll(); //select Member
        for(Member m: members){
            System.out.println("member = " + m);
            System.out.println("member.team = " + m.getTeam().getClass());
            System.out.println("member.team = " + m.getTeam().getName());
        }


    }

    @Test
    public void queryHint(){
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        Member findMember = memberRepository.findReadOnlyByUsername("member1");//걍 읽기만 할래
        findMember.setUsername("member2"); //더티체킹할때 메모리 소요됨, 원래값이랑 변경값 둘 다 갖고 있어야하니

        em.flush();
    }

    @Test
    public void lock(){

        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush();
        em.clear();

        List<Member> findMember = memberRepository.findLockByUsername("member1");//걍 읽기만 할래

        em.flush();

    }

    @Test
    public void callCustom(){
        List<Member> members = memberRepository.findMemberCustom();
    }
}