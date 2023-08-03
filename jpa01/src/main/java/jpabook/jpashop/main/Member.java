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

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


}
