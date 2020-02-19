package infrastructure.datasource.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.KugaDomainException;
import domain.model.member.Member;
import domain.model.member.MemberName;
import domain.model.member.MemberRepository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    @Autowired
    private MemberJpaRepository repository;

    @Override
    public List<Member> findByTeamId(String teamId) {
        return repository.findByTeamId(teamId)
                .stream()
                .map(entity -> toDomainEntity(entity))
                .collect(Collectors.toList());
    }

    private Member toDomainEntity(MemberEntity entity) throws KugaDomainException {
        Member member = Member.builder().id(id);
        member.setId(entity.getId());
        member.setName(new MemberName(entity.getName()));
        return member;
    }

    @Override
    public Member find(String memberId) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public List<Member> findByNameAndTeamId(MemberName name, String teamId) {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    @Override
    public void save(Member member) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void remove(String memberId) {
        // TODO 自動生成されたメソッド・スタブ

    }
}
