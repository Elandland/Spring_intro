package hello.hellospring.domain;

public class Member {

    private Long id;            //고객이 저장하는거 아님, 구분하기위해 sysetm이 적용하는 아이디
    private String name;            //우리는 id, 이름, 핸드폰 번호 ,비밀번호 필요. 핸드폰 인증은 꼭 안넣어도 될거 같긴 함


    public Long getId() {               //geter setter -> lombok이라는걸로 축소가능 나중에 찾아봐라
        return id;
    }
                                        //repository가 회원정보 객체가 저장되는 저장소(인터페이스)
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
