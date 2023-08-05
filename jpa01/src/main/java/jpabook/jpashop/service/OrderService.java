package jpabook.jpashop.service;


import jpabook.jpashop.main.Delivery;
import jpabook.jpashop.main.Member;
import jpabook.jpashop.main.Order;
import jpabook.jpashop.main.OrderItem;
import jpabook.jpashop.main.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAdress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count); //생성 메서드
        //Order order = new Order(); 이런거 막힘, 생성 메서드가 있기 때문
        //그리고 @NoArgsConstructor(access = AccessLevel.PROTECTED) 있어서 생성자 못 만듦

        //주문
        Order order = Order.createOrder(member,delivery, orderItem);

        //주문 저장
        orderRepository.save(order); //CASCADE 때매 알아서 된다고 하네요, 뭔 소린지는 모름

        return order.getId();
    }

    //주문 취소
    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        //주문 취소
        order.cancel();


    }


    //검색

    /*
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }

    */

}
