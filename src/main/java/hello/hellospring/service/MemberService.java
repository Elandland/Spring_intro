package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Transactional          //jpa는 항상 transactional필요
public class MemberService {

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        //dependency injection 자기가 memberRepository를 만드는게 아니라 외부에서 받아옴.
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){

            //같은 이름이 있는 중복 회원은 안된다 ->우리는 같은 PN으로 하면 됨 (UID를 걍 PN으로 할까)
            validateDuplicateMember(member);    //중복회원 검증
            memberRepository.save(member);
            return member.getId();

        }



    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())       //이미 findByName()의 반환 값이 Optional이므로
                .ifPresent(member1 ->{    //ifPresent -> result의 값이 NULL이 아니면
                    throw new IllegalStateException("이미 존재하는 회원 입니다");
                });
    }

    /**
     *
     * 전체 회원 조회
     */
    public List<Member> findMembers(){      //사용자 다 찾기
            return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberID){     //사용자 하나만 찾기
        return memberRepository.findById(memberID);
    }
}
