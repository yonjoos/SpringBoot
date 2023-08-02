package hello.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository; //DIP 지키네요, 인터페이스에만 의존


    @Autowired
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

    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
