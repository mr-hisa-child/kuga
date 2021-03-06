package webapp.kuga.domain.repository.score;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webapp.kuga.domain.dto.Record;
import webapp.kuga.domain.model.Score;

@Mapper
public interface ScoreRepository {
	public Score select(String id);

	public void insert(Score entity);

	public void update(Score entity);

	public void delete(String id);

	public List<Score> selectByActivityId(String activityId);

	public List<Score> selectWithMemberByActivityId(String activity);

	public List<Record> selectWithMemberByYear(String teamId, int year);
}