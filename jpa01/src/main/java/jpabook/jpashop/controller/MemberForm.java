package jpabook.jpashop.controller;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "name, Mandatory") //이름은 필수로 받을거임
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
