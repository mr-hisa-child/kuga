package infrastructure.datasource.member;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.KugaDomainException;
import domain.model.member.Member;
import domain.model.member.MemberName;
import domain.model.member.MemberNo;
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
                .filter(member -> Objects.nonNull(member))
                .collect(Collectors.toList());
    }

    /**
     * 取得したユーザ情報をドメインオブジェクトに変換し、返却します。
     * 
     * @param entity ユーザ情報
     * @return ドメインオブジェクト
     */
    private Member toDomainEntity(MemberEntity entity) {
        try {
            return Member.getMember(entity.getId(), new MemberName(entity.getName()), new MemberNo(entity.getNo()),
                    entity.getTeamId());
        } catch (KugaDomainException e) {
            return null;
        }
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
