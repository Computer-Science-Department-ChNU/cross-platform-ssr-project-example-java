package ua.edu.chnu.kkn.demo.team;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.chnu.kkn.demo.team.teammember.Gender;
import ua.edu.chnu.kkn.demo.team.teammember.TeamMemberService;

import java.util.List;

@Controller
@RequestMapping("/team")
public class TeamController {

    private final TeamMemberService teamMemberService;

    public TeamController(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }

    @GetMapping
    public String team(Model model, @SortDefault.SortDefaults({ @SortDefault("teamMemberName.lastName"),
            @SortDefault("teamMemberName.firstName")}) Pageable pageable) {
        model.addAttribute( "title", "Team Members");
        model.addAttribute("teamMembers", teamMemberService.getTeamMembers(pageable));
        return "team/list";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("teamMember", new CreateTeamMemberFormData());
        model.addAttribute("genders", List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER));
        return "team/edit";
    }

    @PostMapping("/create")
    public String createUser(@Validated(TeamMemberValidationGroupSequence.class)
                                 @ModelAttribute("teamMember") CreateTeamMemberFormData formData,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER));
            return "team/edit";
        }
        teamMemberService.createTeamMember(formData.toParameters());
        return "redirect:/team";
    }
}
