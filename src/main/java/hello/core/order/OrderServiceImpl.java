package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    // @Autowired 필드 주입
    // DI 컨테이너 안에서 밖에 사용 못해 좋지 않음
    // 순수 자바 테스트 불가능
    private final MemberRepository memberRepository;
    //@Qualifier("mainDiscountPolicy")
    @MainDiscountPolicy
    private final DiscountPolicy discountPolicy;

    // 이렇게 수정자에 @Autowired를 넣으면 주입이됨
    /*@Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    // 생성자가 한개면 @Autowired 생략 가능
    // @Autowired(required = false)
    // @RequiredArgsConstructor가 final을 붙은 애를 찾아서 생성자를 만들어준다

    /*public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    // 일반 메서드 주입
    /*@Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 필드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}