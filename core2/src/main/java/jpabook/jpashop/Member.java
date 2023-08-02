package jpabook.jpashop;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String username;

    public void SetUsername(String memberA) {
        username = memberA;
    }
}
