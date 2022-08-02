package hello.hellospring.service;
import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
// 통합테스트 ( 스프링까지 연동해서 테스트 )
@SpringBootTest         //test 할 시 필요
@Transactional          //test가 끝나면 쿼리했던 것을 롤백함. db에 반영x

class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;         //test코드 이기 때문에 autowired써도 됨. 기존 코드는 생성자 주입이 좋음
    @Autowired MemberRepository memberRepository;


    //clear store 필요 x          Transactional이 대체

    @Test           //회원가입
    public void signup() throws Exception {               //이상하게 class 명을 한글로 하면 No tests were found 오류가 뜸 class명을 영어로 해야 됨.
                                                           //근데 class 각각 말고 전체 class(MemberServiceIntergrationTset)로 돌리면 돌아감.
        //given(입력값)                                    //encoding 문제로 추정
        Member member = new Member();
        member.setName("임찬솔");

        //when(실행시)
        Long saveID = memberService.join(member);

        //then(결과값)
        Member findMember = memberService.findOne(saveID).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test       //중복_회원_예외
    public void dup_signup_e() throws Exception{
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();      //이름 중복 회원
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e= assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //뒤의 memberService.join(member2)를 실행할때 앞의 IllegalStateException이 발생해야 정상

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다");


    }


}