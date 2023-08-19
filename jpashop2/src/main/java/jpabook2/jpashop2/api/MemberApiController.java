package jpabook2.jpashop2.api;


import jpabook2.jpashop2.domain.Member;
import jpabook2.jpashop2.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@ResponseBody
public class MemberApiController {
    private final MemberService memberService;

    public MemberApiController(MemberService memberService){
        this.memberService = memberService;
    }


    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
         Long id = memberService.join(member);
         return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberResponse{
        private Long id;

        public CreateMemberResponse(Long id){
            this.id = id;
        }
    }

}
