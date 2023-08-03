package jpabook.jpashop.main;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY) //(fetch = FetchType.EAGER) 이거보다는 order join 쓰세요, 기본 fetch 가 EAGER
    @JoinColumn(name="mamber_id") //외래키
    private Member member;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //기본 fetch가 lazy
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //1:1은 FK누가 하든.., 연관관계 주인을 정해야함
    @JoinColumn(name="delivery_id") //연관관계 주인이다
    private Delivery delivery;


    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) //enum 은 필요
    private OrderStatus status;

    //=======연관관계 메서드=========
    //양방향일때 셋팅하면 좋습니다
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this); //add는 List<>의 함수, getOrders()는 Getter로 생성된 Member의 함수

    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
