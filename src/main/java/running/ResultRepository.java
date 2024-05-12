package running;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ResultRepository extends JpaRepository<Result,Long >{
	List<Result> findByRaceIdOrderByTimeInMinutesAsc(@Param("raceId") Long raceId);
	double findAverageTimeByRaceId(Long raceId);
    
    
}
