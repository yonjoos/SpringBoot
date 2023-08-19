package jpabook2.jpashop2.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {

    /*
    #################  attributes  ##############
     */

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime orderDate;


    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    /*
    ######################  연관관계 #######################
     */

    // MEMBER 1 : N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // ORDER_ITEM N : 1, 양방향
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    // DELIVERY 1 : 1
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;


    /*
    ################# 연관관계 편의 메서드 ###################
     */

    public void setMember(Member member){
        this.member = member; //DB에 한번 갔다오면 알아서 매핑이야 해주겠지만 그래도 해줍시다
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    /*
    ################# 비즈니스 로직 ###################
     */
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems){
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        for(OrderItem orderItem : orderItems){
            orderItem.setOrder(order);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMP){
            throw new IllegalStateException("이미 출발, 빠꾸 ㄴㄴ");
        }
        this.setStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem : orderItems){
            orderItem.cancel();
        }
    }

    public int getTotalPrice(){
         int totalPrice = 0;
         for(OrderItem orderItem : orderItems){
             totalPrice += orderItem.getTotalPrice();
         }
         return totalPrice;
    }

}
