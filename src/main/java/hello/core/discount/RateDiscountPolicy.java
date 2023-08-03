package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
//@Qualifier("mainDiscountPolicy")
//@Primary
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP ){
            return price * discountPercent / 100;
        }else {
            return 0;
        }
    }
}
// ===============================================================================================================
//@Qualifier는 추가 구분자를 붙여주는 방법이다. 주입 시 추가적인 방법을 제공하는 것이지 빈 이름을 변경하는 것은 아니다.
//등록한 이름을 적어준다.
//@Qualifier로 주입할 때 @Qualifier("mainDiscountPolicy")를 못찾으면 어떻게 될까?
//그러면 mainDiscountPolicy라는 이름의 스프링 빈을 추가로 찾는다.
//하지만 경험상 @Qualifier는@Qualifier를 찾는 용도로만 사용하는게 명확하고 좋다
// @Qualifier의 단점은 주입 받을 때 모든 코드에 @Qualifier를 붙여주어야 한다는 점이다
// ===============================================================================================================
//@Primary
//@Primary는 우선순위를 정하는 방법이다.
//@Autowired 시에 여러 빈 매칭되면 @Primary가 우선권을 가진다. (자주 사용 !! )
//반면에 @Primary를 사용하면 이렇게 @Qualifier를 붙일 필요가 없다.
// ===============================================================================================================
//@Primary / @Qualifier 활용

//코드에서 자주 사용하는 메인 데이터베이스의 커넥션을 획득하는 스프링 빈이 있고, 코드에서 특별한 기능으로 가끔 사용하는
//서브 데이터베이스의 커넥션을 획득하는 스프링 빈이 있다고 생각해보자.
//메인 데이터베이스의 커넥션을 획득하는 스프링 빈은 @Primary를 적용해서 조회하는 곳에서 @Qualifier 지정 없이 편리하게 조회하고,
//서브 데이터베이스 커넥션 빈을 획을 학 때는 @Qualifier를 지정해서 명시적으로 획득하는 방식으로 사용하면 코드를 깔끔하게 유지할 수 있다.
//물론 이때 메인 데이터베이스의 스프링 빈을 등록할 때 @Qualifier 를 지정해주는 것은 상관없다.
// ===============================================================================================================
// 우선 순위

// @Primary 는 기본값 처럼 동작하는 것이고, @Qualifier는 매우 상세하게 동작한다.
// 이런 경우 어떤 것이 우선권을 가져 갈까?
// 스프링은 자동보다는 수동이, 넓은 범위의 선택권 보다는 좁은 범위의 선택권이 우선 순위가 높다.
// 따라서 여기서도 @Qualifier가 우선권이 높다.
// ===============================================================================================================
