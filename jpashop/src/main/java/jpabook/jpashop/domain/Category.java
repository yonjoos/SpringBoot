package jpabook.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class Category {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;


    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"), //내가 조인해야 하는 거
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID") //반대쪽이 조인해야 하는 거
    )
    private List<Item> items = new ArrayList<>();
}
