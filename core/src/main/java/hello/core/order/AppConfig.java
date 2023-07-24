package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;

public class AppConfig {

    /*
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository()); //이거는 역할 안 보이잖아
    }

    */

    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository()); //new 2개면 충돌나니까 얘꺼는 지워
    }
    private MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }


    /*
    public OrderService orderService(){
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
     */


    //이렇게 바꾸면 역할이 확실히 보임....
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }


    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }
}
