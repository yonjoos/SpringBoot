package jpabook.jpashop.main.item;


import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.main.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter //(바깥에서)를 넣는 게 아니라, 비즈니르 로직(Entity 안에서, 객체지향적)을 가지고 변경 해야함
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


    //======== 비즈니스 로직 ===========
    //Entitiy 안에 조절하는 로직이 있어야돼용


    // 재고 증가
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int realStock = stockQuantity - quantity;
        if (realStock < 0)
            throw new NotEnoughStockException("need more stock"); //Exception 만들어야됨
        this.stockQuantity = realStock;
    }


}
