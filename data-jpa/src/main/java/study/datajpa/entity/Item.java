package study.datajpa.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

    @Id @GeneratedValue
    private Long id;

    //private long id; 로 하면 long은 java의 primitive 이기 때문에 null 값을 가지지 못하고 대신 0 가짐
}
