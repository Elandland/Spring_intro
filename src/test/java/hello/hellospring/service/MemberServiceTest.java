package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;            //static import
import static org.junit.jupiter.api.Assertions.*;
// 단위 테스트 ( 스프링 컨테이너 없이 단위 단위로 끊어서 테스트 )
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforEach(){
        //각 메소드 실행전에 리포지 만들어서 서비스에 넣어줌.(clear하기 위에 리포지 필요, 근데 리포지를 생성해서 쓰면 서비스 안에서 만든
        //리포지랑 다른 값이 되므로 만들어서 넣어줘서 동일시 시킴
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach(){

        memberRepository.clearstore();

    }

    @Test
    void 회원가입() {
        //given(입력값)
        Member member = new Member();
        member.setName("spring");

        //when(실행시)
        Long saveID = memberService.join(member);

        //then(결과값)
        Member findmember = memberService.findOne(saveID).get();
        assertThat(member.getName()).isEqualTo(findmember.getName());

    }

    @Test
    public void 중복_회원_예외(){
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

/*        try {
            memberService.join(member2);        //중복 회원 가입 시도
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다");
        }
*/

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}