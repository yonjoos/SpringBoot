package jpabook.jpashop.domain.item;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;


    @Embedded
    private Address address;


    //그니까 겹치는거 문제라는 거잖아
    @OneToMany(mappedBy = "order")
    private List<Order> orders = new ArrayList<>();


}
