package hello.core.member;

public class MemberServiceImpl  implements  MemberService{
    // MemberServiceImpl은 MemberRepository와 MemoryMemberRepository 둘 다 의존하고 있다.
    // 즉, 추상화에도 의존하고 , 구체화에도 의존한다. DIP 위반
    // 나중에 변경이 있을 때, 문제가 생길 수 있다.

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
