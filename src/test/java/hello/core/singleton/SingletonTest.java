package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){

        AppConfig appConfig = new AppConfig();

        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        // new SingletonService();
        // private 으로 new 키워드를 막아두었다.
        // 호출할 때 마다 같은 객체 인스턴스를 반환하는 것을 확인할 수 있다.

        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        //SameAs ==
        //NotSameAs !=
        //Equal equal
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
      //  AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


        //1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService" , MemberService.class);
        MemberService memberService2 = ac.getBean("memberService" , MemberService.class);


        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);

        // 스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유해서 효율적으로 재사용
        // 사용할 수 있다.
    }




        // 싱글돈 패턴을 구현하는 방법은 여러가지가 있다. 여기서는 객체를 미리 생성해두는 가장
        // 단순하고 안전한 방법을 선택했다.

        // 싱글톤 패턴을 적용하면 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라,
        // 이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다.
        // 하지만 싱글톤 패턴은 다음과 같은 수 많은 문제점들을 가지고 있다.

        /*
        *  < 싱글톤 패턴 문제점 >
            1. 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
            2. 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP를 위반한다.
            3. 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
            4. 테스트하기 어렵다.
            5. 내부 속성을 변경하거나 초기화 하기 어렵다.
            6. private 생성자로 자식 클래스를 만들기 어렵다.
            7. 결론적으로 유연성이 떨어진다.
            8. 안티패턴으로 불리기도 한다.
        * */





}
