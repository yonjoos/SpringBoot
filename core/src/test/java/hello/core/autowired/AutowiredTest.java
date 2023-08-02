package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{
        @Autowired(required = false) //의존 관계가 없으면 메서드(수정자) 자체가 호출이 안 됨
        public void setNoBean1(Member noBean1){ //Member는 스프링 관련 빈이 아니죠...spring container에 관리되는게 없음
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired //호출은 되는데 null로 들어옴
        public void setNoBean2(@Nullable Member noBean1){
            System.out.println("noBean2 = " + noBean1);
        }

        @Autowired //spring bean이 없으면 optional.empty로 넣어줌
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
