package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//jdbctemplate 라이브러리가 코드를 간결하게 줄여줌
//디자인 패턴중에 탬플릿매서드 패턴으로 해결
public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired              //생성자가 class에 1개만 있으면 spring bin으로 등록되서 Autowired를 생략해도됨
    public JdbcTemplateMemberRepository(DataSource dataSource) {    //spring bin이 datasource injection해줌
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {     //table명이랑 pk이름만 있으면 쿼리가 필요 x (simpleJdbctemplate)

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());


        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id = ?",memberRowMapper(),id);        //앞에 쿼리, 뒤에 결과값을 rowmapper해줘야됨
        return result.stream().findAny();       //optional로 반환

    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?",memberRowMapper(), name);        //앞에 쿼리, 뒤에 결과값을 rowmapper해줘야됨
        return result.stream().findAny();       //optional로 반환
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member",memberRowMapper());
    }

    private RowMapper<Member>memberRowMapper(){         //resultset이 이 함수로 넘어옴      , 얘가 멤버 생성
        return (rs, rowNum) -> {        //람다식
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;

        };

    }




}
