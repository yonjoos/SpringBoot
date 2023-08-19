package jpabook2.jpashop2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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


    /*
    ################# 연관관계 ###################
     */
    // ORDERS N : 1
    @OneToMany(mappedBy = "member") //양방향
    private List<Order> orders = new ArrayList<>();





}
