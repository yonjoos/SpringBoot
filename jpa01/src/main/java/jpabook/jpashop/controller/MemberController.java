package jpabook.jpashop.controller;


import jpabook.jpashop.main.Address;
import jpabook.jpashop.main.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        /*
        What is MODEL??........
         */
        model.addAttribute("memberForm", new MemberForm()); //memberForm이라는 빈 껍데기를...
        return "members/createMemberForm";

    }

    @PostMapping("/members/new") //데이터를 실제로 등록
    public String create(@Valid MemberForm form, BindingResult result) //Why not @Valid Members member?
    {
        //Because, not always input data == form of Members
        if(result.hasErrors()) {
            return "members/createMemberForm"; //어떤 에러가 있는지 이 화면에서 확인 가능, MemberForm에 @NotEmpty에 적어놨던 에러
        }
        //@Valid : MemberForm에 있는 @NotEmpty...이거 불러와줌
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setUsername(form.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/"; //첫 번째 페이지로 넘어가

    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); //JPA에서 JPQL로 멤버 목록 줌
        model.addAttribute("members", members);
        return "members/memberList"; //화면이 memberList 로 바꿈
    }


}
