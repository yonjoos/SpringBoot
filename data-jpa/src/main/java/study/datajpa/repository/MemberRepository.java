package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> , MemberRepositoryCustom{

    //구현체가 없는데 어떻게 동작해요? : 인터페이스보고 jpa가 구현체 만들어

    // 부가기능 추가하고싶으면?
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    //없애도 됩니다, 이름 찾아서 있으면
    List<Member> findByUsername(@Param("username") String name);

    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();


    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();


    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") Collection<String> names);

    /*
    ########### Return-type doesn't matter ##############
     */

    List<Member> findListByUsername(String username);

    Member findMemberByUsername(String username);

    Optional<Member> findOptionalByUsername(String username);

    //쿼리 분리 가능
    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m.username) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);


    @Modifying(clearAutomatically = true) //쿼리 나간 후 clear 자동으로 해줌
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll(); //멤버랑 팀 동시에 검색하고싶어, 패치조인

    @EntityGraph(attributePaths = {"team"}) //JPQL랑 패치조인 동시에 쓰고싶음
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph(); //멤버랑 팀 동시에 검색하고싶어

    //@EntityGraph(attributePaths = {"team"}) //메서드 이름으로 지은거에 적용도 가능
    @EntityGraph("Member.all")
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
    Member findReadOnlyByUsername(String username);

    //select for update, 딴 애들 건들지 lock 걸 수 있음


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);


}
