package running;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RaceWebController {

    private final RaceRepository raceRepository;
    private final ResultRepository resultRepository;

    public RaceWebController(RaceRepository raceRepository, ResultRepository resultRepository) {
        this.raceRepository = raceRepository;
        this.resultRepository = resultRepository;
    }

    @GetMapping("")
    public String showRaceList(Model model) {
        model.addAttribute("races", raceRepository.findAll());
        return "race-list";
    }

    @GetMapping("/addRace")
    public String showAddRaceForm(Race race) {
        return "add-race";
    }

    @PostMapping("/addRace")
    public String addRace(@Validated Race race, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-race";
        }

        raceRepository.save(race);
        return "redirect:/";
    }

    @GetMapping("/raceDetails/{id}")
    public String showRaceDetails(@PathVariable Long id, Model model) {
        Runner race = raceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid race id: " + id));

        model.addAttribute("race", race);
        model.addAttribute("results", resultRepository.findByRaceIdOrderByTimeInMinutesAsc(id));
        return "race-details";
    }
}

