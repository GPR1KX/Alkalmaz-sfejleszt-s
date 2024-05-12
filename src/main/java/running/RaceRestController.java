package running;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RaceRestController {

    private final RunnerRepository runnerRepository;
    private final RaceRepository raceRepository;
    private final ResultRepository resultRepository;

    public RaceRestController(RunnerRepository runnerRepository, RaceRepository raceRepository, ResultRepository resultRepository) {
        this.runnerRepository = runnerRepository;
        this.raceRepository = raceRepository;
        this.resultRepository = resultRepository;
    }

    @GetMapping("/getRunners")
    public List<Runner> getAllRunners() {
        return runnerRepository.findAll();
    }

    @PostMapping("/addRunner")
    public Runner addRunner(@RequestBody Runner runner) {
        return runnerRepository.save(runner);
    }

    @GetMapping("/getRaceRunners/{id}")
    public List<Result> getRaceRunners(@PathVariable Long id) {
        Optional<Runner> optionalRace = raceRepository.findById(id);
        if (optionalRace.isPresent()) {
            Runner race = optionalRace.get();
            return resultRepository.findByRaceIdOrderByTimeInMinutesAsc(race.getId());
        }
        return null;
    }



    @PostMapping("/updateRace")
    public Race updateRace(@RequestBody Race race) {
        return raceRepository.save(race);
    }

    @PostMapping("/addResult")
    public Result addResult(@RequestBody Result result) {
        return resultRepository.save(result);
    }

    @GetMapping("/getAverageTime/{id}")
    public double getAverageTime(@PathVariable Long id) {
        return resultRepository.findAverageTimeByRaceId(id);
    }
}

