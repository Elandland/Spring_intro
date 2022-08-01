package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;        //assertthat을 바로 칠 수 있음

class MemoryMemberRepositoryTest {      //다른데서 갖다 쓸 필요 없으니까 public안해도 됨.

    MemoryMemberRepository repository = new MemoryMemberRepository();


    @AfterEach      //메소드가 끝날때마다 실행, 콜백 메소드 , clear 없으면 repository에 있는 정보들이 겹쳐서 에러
    public void afterEach(){

        repository.clearstore();
    }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, null);// 기대하는 값이 앞에. 뒤에는 넣을값(우리가 저장한 member객체), 이 줄은 주피터가 제공하는거
        assertThat(member).isEqualTo(result);
    }


    @Test
    public void findbyName(){

        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);


        Member result = repository.findByName("Spring1").get();

        assertThat(result).isEqualTo(member1);
    }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);


        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}

//ttd test주도 개발. test로 틀을 먼저 만들고 구현 클래스를 만듦
