package webapp.kuga.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import webapp.kuga.domain.model.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

}
