package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.entity.Member;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {

    //구현체가 없는데 어떻게 동작해요? : 인터페이스보고 jpa가 구현체 만들어

    // 부가기능 추가하고싶으면?
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String name);
}
