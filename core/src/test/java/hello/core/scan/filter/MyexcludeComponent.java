package hello.core.scan.filter;


import java.lang.annotation.*;

@Target(ElementType.TYPE) //TYPE 은 클래스 레벨
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyexcludeComponent { //얘가 붙은 것은 컴포넌트에 추가에서 제외
}