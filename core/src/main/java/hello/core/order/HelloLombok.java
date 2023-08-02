package hello.core.order;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter //게터 세터 자기가 알아서 만들어줌
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] arg){
        HelloLombok helloLombok= new HelloLombok();
        helloLombok.setName("asdfas");

        String name = helloLombok.getName();
        System.out.println("name = " + helloLombok);
    }
}
