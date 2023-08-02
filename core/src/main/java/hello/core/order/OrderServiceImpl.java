package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


//@Configuration
@Component
public class OrderServiceImpl implements OrderService{
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //private final MemberRepository memberRepository ; //그냥 생성자 주입
    //private MemberRepository memberRepository; //수정자 주입
    //@Autowired private MemberRepository memberRepository; //필드 주입
    //@Autowired private DiscountPolicy discountPolicy;
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;


    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //discount policy 바꾸려면 어쨌든 클래스 수정해야함 바로 위에랑 아래 줄처럼
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //private final DiscountPolicy discountPolicy; // 인터페이스에만 의존하죠?, 따라서 추상화에만 의존합니다
    //근데 이것만 하면 null point exeption 일어납니다
    //DiscountPolicy 의 구현객체를 누군가가 대신 만들어줘야함, 누가?


    //일반 메서드 주입
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
