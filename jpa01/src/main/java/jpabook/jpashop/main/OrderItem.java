package jpabook.jpashop.main;


import jpabook.jpashop.main.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name ="order_id") //오더는 하나의 오더 아이디 가짐
    private Order order;

    private int orderPrice; //주문 당시 가격
    private int count;
}
