package jpabook2.jpashop2.domain.item;


import jpabook2.jpashop2.domain.CategoryItem;
import jpabook2.jpashop2.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;



    /*
    ################# 연관관계 ######################
    */

    @OneToMany(mappedBy = "item", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CategoryItem> categoryItems = new ArrayList<>();

    /*
    ################# 비즈니스 로직  ###################
     */
    public void addStock(int quantity){
         this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int realStock = this.stockQuantity - quantity;
        if(realStock < 0){
            throw new NotEnoughStockException("Need More Stock");
        }

        this.stockQuantity = realStock;
    }
}
