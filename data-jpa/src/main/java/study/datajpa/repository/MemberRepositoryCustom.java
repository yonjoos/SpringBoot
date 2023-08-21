package study.datajpa.repository;

import study.datajpa.entity.Member;

import java.util.List;

//쿼리DSL 쓸 떄 많이 씀
public interface MemberRepositoryCustom {
    List<Member> findMemberCustom();
}
