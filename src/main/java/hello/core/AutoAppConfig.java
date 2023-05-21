package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // @ComponentScan은 이름 그대로 @Componet 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
//        basePackages = "hello.core.member", // 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.
//        basePackageClasses = AutoAppConfig.class, //지정한 클래스의 패키지를 탐색 시작 위로 지정한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = Configuration.class)
)// excludeFilters -> 설정 정보를 사용함으로 컴포넌트 스캔 대상에서 제외 시켜준다. -> classes = Configuration.class -> 즉, Configuration
// 어노테이션이 있는 파일은 제외시킨다.
public class AutoAppConfig { // 만약 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.

//    @Bean(name = "memoryMemberRepository") // 이 경우 수동 빈 등록이 우선권을 가진다.
//    // (수동 빈이 자동 빈을 오버라이딩 해버린다.)
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}

