package jpabook.jpashop.main.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("B")
@Getter @Setter
public class Book extends Item{


    @Id @GeneratedValue
    private String author;
    private String isbn;
}
