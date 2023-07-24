package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository ;


    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //discount policy 바꾸려면 어쨌든 클래스 수정해야함 바로 위에랑 아래 줄처럼
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    private DiscountPolicy discountPolicy; // 인터페이스에만 의존하죠?, 따라서 추상화에만 의존합니다
    //근데 이것만 하면 null point exeption 일어납니다
    //DiscountPolicy 의 구현객체를 누군가가 대신 만들어줘야함, 누가?


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
}
