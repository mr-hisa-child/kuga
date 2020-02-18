package infrastructure.datasource.member;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

	private Member toDomainEntity(MemberEntity entity) {
		Member member = new Member();
		member.setId(entity.getId());
		member.setName(new MemberName(entity.getName()));
		return member;
	}
}
