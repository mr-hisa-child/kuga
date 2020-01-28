package webapp.kuga.domain.repository.member;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webapp.kuga.domain.model.Member;

@Mapper
public interface MemberRepository {
    public Member select(String id);

    public void insert(Member entity);

    public void update(Member entity);

    public void delete(String id);

    public List<Member> selectByTeamId(String teamId);
}