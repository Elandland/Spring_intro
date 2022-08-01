package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);         //회원저장
    Optional<Member> findById(Long id); //findby로 찾았는데 NULL일때 대비해서 Optional씀 (ID로 회원찾는거)
    Optional<Member> findByName(String name);

    List<Member> findAll();
}
