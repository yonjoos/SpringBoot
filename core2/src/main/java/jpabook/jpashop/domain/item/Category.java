package jpabook.jpashop.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category { //얘랑 Item을 연결해야함, item class 확인하기
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> categories = new ArrayList<>();//중간에 낑구는거, 근데 실제로는 쓰지 마세요, 더이상 변경 못 해서
}
