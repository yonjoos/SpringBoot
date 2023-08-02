package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
//@Qualifier("fixDiscountPolicy") // 해결책2
@Primary //해결책2
public class FixDiscountPolicy implements DiscountPolicy { //alt + Enter요

    private int discountFixAmount = 1000; //1000 할인
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){ //VIP 는 1000원
            return discountFixAmount;
        }
        else return 0; //아니면 할인 없어
    }
}
