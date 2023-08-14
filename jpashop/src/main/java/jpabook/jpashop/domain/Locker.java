package jpabook.jpashop.domain;


import javax.persistence.*;

@Entity
public class Locker {

    @Id @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;


    @OneToOne(mappedBy = "locker") //양방향일 경우
    private Member member;
}
