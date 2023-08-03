package jpabook.jpashop.main;


import jpabook.jpashop.main.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity@Getter@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name="category_id")
    private Long id;

    private String name;



    @ManyToMany
    @JoinTable(name="category_item",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")
    ) //중간 테이블, 1:다들로 다:다 만들어줘야함,
    private List<Item> items = new ArrayList<>();

}
