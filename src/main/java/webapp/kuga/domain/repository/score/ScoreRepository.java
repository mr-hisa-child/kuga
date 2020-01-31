package webapp.kuga.domain.repository.score;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import webapp.kuga.domain.model.Score;

@Mapper
public interface ScoreRepository {
    public Score select(String id);

    public void insert(Score entity);

    public void update(Score entity);

    public void delete(String id);

    public void deleteByActivityIdAndMemberId(String activityId, String memberId);

    public List<Score> selectByActivityId(String activityId);
}