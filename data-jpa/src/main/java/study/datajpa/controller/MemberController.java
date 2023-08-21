package study.datajpa.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberRepository;
import study.datajpa.repository.MemberRepositoryCustom;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id){
        Member member = memberRepository.findById(id).get();
        return member.getUsername();

    }

    // Spring JPA injects member into member att
    @GetMapping("/members2/{id}")
    public String findMember2(@PathVariable("id") Member member){
        return member.getUsername();

    }

    @GetMapping("/members") //page의 default값 setting 은 yml 에서 변경할 수 있어요
    public Page<Member> list(Pageable pageable){
        Page<Member> page = memberRepository.findAll(pageable);
        return page;
    }

    @PostConstruct
    public void init(){
        for(int i = 0; i < 100; i++){
            memberRepository.save(new Member("user" + i, i));
        }

    }



}
