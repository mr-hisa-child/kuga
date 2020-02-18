package infrastructure.datasource.activity;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.model.activity.ActivityRepository;
import webapp.kuga.domain.model.Activity;

@Repository
public class ActivityRepositoryImpl implements ActivityRepository {
	@Autowired
	private ActivityJpaRepository repository;

	@Override
	public List<Activity> findByTeamId(String teamId) {
		return repository.findByTeamId(teamId)
				.stream()
				.map(entity -> toDomainEntity(entity))
				.collect(Collectors.toList());
	}

	@Override
	public List<Activity> findByTeamIdAndYear(String teamId, int year) {
		return repository.findByTeamIdAndYear(teamId, year)
				.stream()
				.map(entity -> toDomainEntity(entity))
				.collect(Collectors.toList());
	}

	private Activity toDomainEntity(ActivityEntity entity) {
		Activity activity = new Activity();
		activity.setDay(entity.getDay());
		activity.setMonth(entity.getMonth());
		activity.setYear(entity.getYear());
		activity.setTitle(entity.getTitle());
		return activity;
	}
}
