package jpabook2.jpashop2.domain;


import jpabook2.jpashop2.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    /*
    ##### PARENT ???
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    /*
    #######################  연관관계 #####################
     */

    // CATEGORYITEM
    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CategoryItem> categoryItems = new ArrayList<>();

    /*
    #######################  연관관계 편의 메서드 #####################
     */
    public void addChild(Category child){
        this.child.add(child);
        child.setParent(this);
    }


}
