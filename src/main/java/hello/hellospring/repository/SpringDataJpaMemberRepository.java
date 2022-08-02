package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//interface는 다중상속가능
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long> ,MemberRepository{

    //JPQL로 보면 select m from Member where m.name = ?으로 쏴줌. findBy ~~ 하면 ~~에 대한 쿼리 작성
    @Override
    Optional<Member> findByName(String name);
}
