package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{


    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;   // 0,1,2 처럼 key값 생성.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();         //loop돌면서 입력한 name이랑 같은 name이 member에 있는지, 찾으면 바로 반환(any) 없으면 NULL반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());     //key값이 id, values값이 member객체
    }

    public void clearstore(){

        store.clear();  //store를 비움
    }
}
