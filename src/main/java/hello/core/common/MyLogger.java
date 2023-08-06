package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value = "request")
@Scope(value = "request" , proxyMode = ScopedProxyMode.TARGET_CLASS) // 가짜를 만든다. 프록시를 만든다
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" +"["+ requestURL+ "] " + message );
    }

    @PostConstruct
    public void init(){
         uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create : " + this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close : " + this);
    }
}

// proxyMode = ScopedProxyMode.TARGET_CLASS 를 추가해주자

// 적용 대상이 인터페이스가 아닌 클래스면 'TARGET_CLASS' 를 선택
// 적용 대상이 인터페이스면 'INTERFACES' 를 선택
// 이렇게 하면 MyLogger 의 가짜 프록시 클래스를 만들어두고 HTTP request 와 상관 없이 가짜 프록시 클래스를 다른 빈에 미리 주입해 둘 수 있다.



