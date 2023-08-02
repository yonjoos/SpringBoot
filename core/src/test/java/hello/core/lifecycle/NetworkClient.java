package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {
    private String url;
    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);

    }

    public void setUrl(String url){
        this.url = url;
    }

    public void connect(){ //(2)-1
        System.out.println("connet : " + url);
    }

    public void call(String message){ //(3)-1
        System.out.println("call : " + url + " message : " + message);
    }

    public void disconnect(){
        System.out.println("closed " + url);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        //의존관계 주입이 끝나면 호출하겠다
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        disconnect();
    }
}
