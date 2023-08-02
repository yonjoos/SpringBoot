package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest(){
        //ConfigurableApplicationContext가 Annotation저거의 상위 클래스이기 때문에 담을 수 있다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{
        @Bean
        public  NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient(); //(1)
            networkClient.setUrl("http://aaa"); //(4) setter로 url 입력...생성자 생성 후에 입력될 수 있다
            return networkClient;
        }
    }
}
