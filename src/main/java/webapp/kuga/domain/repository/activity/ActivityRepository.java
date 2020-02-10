package webapp.kuga.domain.repository.activity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webapp.kuga.domain.model.Activity;

@Mapper
public interface ActivityRepository {
	public Activity select(String id);

	public void insert(Activity entity);

	public void update(Activity entity);

	public void delete(String id);

	public List<Activity> selectByTeamId(String teamId);

	public List<Activity> selectByTeamIdAndYear(String teamId, int year);
}