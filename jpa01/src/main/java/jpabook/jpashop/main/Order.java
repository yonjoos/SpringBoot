package jpabook.jpashop.main;


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

    @ManyToOne
    @JoinColumn(name="mamber_id") //외래키
    private Member member;


    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();


    @OneToOne //1:1은 FK누가 하든.., 연관관계 주인을 정해야함
    @JoinColumn(name="delivery_id") //연관관계 주인이다
    private Delivery delivery;


    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) //enum 은 필요
    private OrderStatus status;

}
