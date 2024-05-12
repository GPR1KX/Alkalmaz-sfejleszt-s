package running;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TestDataInitializer {
	
	@Autowired
    private RunnerRepository runnerRepository;

    @Autowired
    private RaceRepository raceRepository;

    @Autowired
    private ResultRepository resultRepository;
    
	 @Bean
	    public CommandLineRunner initializeTestData(RunnerRepository runnerRepository, RaceRepository raceRepository, ResultRepository resultRepository) {
	        return args -> {
	            // Futók hozzáadása
	        	Runner runnerEntity1 = new Runner();
	            runnerEntity1.setName("John Doe");
	            runnerEntity1.setAge(30);
	            runnerEntity1.setGender("Male");
	            runnerRepository.save(runnerEntity1);

	            Runner runnerEntity2 = new Runner();
	            runnerEntity2.setName("Jane Doe");
	            runnerEntity2.setAge(25);
	            runnerEntity2.setGender("Female");
	            runnerRepository.save(runnerEntity2);

	            Runner runnerEntity3 = new Runner();
	            runnerEntity3.setName("Alice Smith");
	            runnerEntity3.setAge(35);
	            runnerEntity3.setGender("Female");
	            runnerRepository.save(runnerEntity3);

	            Runner runnerEntity4 = new Runner();
	            runnerEntity4.setName("Bob Johnson");
	            runnerEntity4.setAge(40);
	            runnerEntity4.setGender("Male");
	            runnerRepository.save(runnerEntity4);

	            // Versenyek hozzáadása
	            Race raceEntity1 = new Race();
	            raceEntity1.setName("Marathon");
	            raceEntity1.setDistance(42.195);
	            raceRepository.save(raceEntity1);

	            Race raceEntity2 = new Race();
	            raceEntity2.setName("Half Marathon");
	            raceEntity2.setDistance(21.0975);
	            raceRepository.save(raceEntity2);

	            

	         // Eredmények hozzáadása
	            resultRepository.save(new Result(runnerEntity1, raceEntity1, 180)); // John Doe, Marathon, 3 óra
	            resultRepository.save(new Result(runnerEntity2, raceEntity1, 210)); // Jane Doe, Marathon, 3 óra 30 perc
	            resultRepository.save(new Result(runnerEntity3, raceEntity1, 195)); // Alice Smith, Marathon, 3 óra 15 perc
	            resultRepository.save(new Result(runnerEntity4, raceEntity1, 240)); // Bob Johnson, Marathon, 4 óra
	            resultRepository.save(new Result(runnerEntity1, raceEntity2, 90)); // John Doe, Half Marathon, 1 óra 30 perc
	            resultRepository.save(new Result(runnerEntity2, raceEntity2, 100)); // Jane Doe, Half Marathon, 1 óra 40 perc
	        };
	    }
}
