package jpabook.jpashop.main;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;
    private String username;

    @Embedded
    private Address adress;

    @OneToMany(mappedBy = "member") //연관관계 주인이 아니예요, ordertable에 있는 member 필드에 의해 매핑, 여기 값 바꾼다고 FK 바뀌지 않음
    private List<Order> orders = new ArrayList<>();


}
