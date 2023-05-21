package hello.core;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// 중요하다!!!

// 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, "구현 객체를 생성"하고 "연결"하는
// 책임을 가지는 별도의 설정 클래스
//AppConfig는 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성"한다.
//즉, 객체의 생성과 연결은 "AppConfig"가 담당한다.

//MemberServiceImpl
//MemoryMemberRepository
//OrderServiceImpl
//FixDiscountPolicy

//AppConfig는 생성한 객체 인스턴스의 참조(래퍼런스)를 "생성자를 통해서 주입(연결)"해준다.
//MemberServiceImpl -> MemoryMemberRepository
//OrderServiceImpl -> MemoryMemberRepository , FixDiscountPolicy4

//정리
//AppConfig를 통해서 관심사를 확실하게 분리했다.
//배역, 배우를 생각해보자
//AppConfig는 공연 기획자다.
//AppConfig는 구체 클래스를 선택한다. 배역에 맞는 담당 배우를 선택한다.
//애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다.
//이제 각 배우들은 담당 기능을 실행하는 책임만 지면 된다.
//orderServiceImpl 은 기능을 실행하는 책임만 지면 된다.

@Configuration // 설정 정보에 Configuration을 적용 시켜준다.
public class AppConfig {
    // 중복 코드 , 리팩터링 new MemoryMemberRepository() ,  new FixDiscountPolicy()
    // 역할이 한눈에도 볼 수 있게 처리해줘야 한다.

    //@Bean memberSerivce =  new MemoryMemberRepository()
    //@Bean orderService =  new MemoryMemberRepository()

    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository


    // run을 시켜보니 3번 호출 됨.
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    @Bean
    public MemberService memberService(){
        // MemberService를 요청하면 Impl 리턴 시 MemoryMemberRepository 생성해서 보냄

        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());

    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(),  discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
      //  return new FixDiscountPolicy();
        return new RateDiscountPolicy();

    }
}
