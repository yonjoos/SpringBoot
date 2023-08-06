package jpabook.jpashop.repository;


import jpabook.jpashop.main.OrderStatus;
import jpabook.jpashop.service.OrderService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {

    private String memberName; //회원 이름
    private OrderStatus orderStatus;
}
