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


    //아 복잡학 조인된 거 중에서 category만 필요하니까 item_id로 inverse해주는구낭
    @ManyToMany // !에러 : ManyToOne 으로 오타
    @JoinTable(name="category_item",
            joinColumns = @JoinColumn(name="category_id"),
            inverseJoinColumns = @JoinColumn(name="item_id")
    ) //중간 테이블, 1:다들로 다:다 만들어줘야함, 실전에서는 쓰지 마라
    private List<Item> items = new ArrayList<>(); // !에러 : Items 에 있는 mapped by랑 이름 같게 해야함, 대문자 써서 에러




    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}
