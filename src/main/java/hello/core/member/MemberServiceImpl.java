package hello.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl  implements  MemberService{
    // MemberServiceImpl은 MemberRepository와 MemoryMemberRepository 둘 다 의존하고 있다.
    // 즉, 추상화에도 의존하고 , 구체화에도 의존한다. DIP 위반
    // 나중에 변경이 있을 때, 문제가 생길 수 있다.

    // memoryMemberRepository를 config에서 생성 시킨다.
    // DIP 유지
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //설계 변경으로 MemberServiceImpl 은 MemoryMemberRepository를 의존하지 않는다.
    // 단지 MemberRepository 인터페이스만 의존한다.
    //MemberServiceImpl 입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
    //MemberServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부 AppConfig에서 결정된다.
    //MemberServiceImpl은 이제부터 "의존관계에 대한 고민은 외부"에 맡기고 "실행에만 집중"하면 된다.

    private final MemberRepository memberRepository;

    @Autowired // 의존관계 주입
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
