package running;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceRepository extends JpaRepository<Runner,Long >{

	Race save(Race race);

}
