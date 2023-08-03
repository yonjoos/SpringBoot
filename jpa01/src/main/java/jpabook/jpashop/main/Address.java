package jpabook.jpashop.main;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable //값타입, 수정이 불가능함, 생성할 때만 입력 가능함, thus setter 없음
@Getter
public class Address {

    //기본 생성자 없으면 안 된다고 하기는 하는데
    protected Address() {}

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    private String city;
    private String street;
    private String zipcode;

}
