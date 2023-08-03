package jpabook.jpashop.main.item;


import jpabook.jpashop.main.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //싱글테이블 전략
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items") //Category java 안에 있는 변수명
    private List<Category> categories = new ArrayList<>();
}
