package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
//@Qualifier("mainDiscountPolicy") 해결책2,
@MainDiscountPolicy //얘랑, 이 클래스를 사용하는 orderImpl도 코드 바꿔줘야함
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }
        else return 0;
    }
}
