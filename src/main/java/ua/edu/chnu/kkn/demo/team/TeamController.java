package ua.edu.chnu.kkn.demo.team;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.edu.chnu.kkn.demo.team.teammember.*;

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
    public String createTeamMemberForm(Model model) {
        model.addAttribute("teamMember", new CreateTeamMemberFormData());
        model.addAttribute("genders", List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER));
        return "team/edit";
    }

    @PostMapping("/create")
    public String createTeamMember(@Validated(CreateTeamMemberValidationGroupSequence.class)
                                 @ModelAttribute("teamMember") CreateTeamMemberFormData formData,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER));
            return "team/edit";
        }
        teamMemberService.createTeamMember(formData.toParameters());
        return "redirect:/team";
    }

    @GetMapping("/{id}")
    public String editTeamMemberForm(@PathVariable("id") TeamMemberId teamMemberId, Model model) {
        TeamMember teamMember = teamMemberService
                .getTeamMember(teamMemberId)
                .orElseThrow(() -> new TeamMemberNotFoundException(teamMemberId));
        model.addAttribute("teamMember", EditTeamMemberFormData.fromTeamMember(teamMember));
        model.addAttribute("genders", List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER));
        model.addAttribute("editMode", EditMode.UPDATE);
        return "team/edit";
    }

    @PostMapping("/{id}")
    public String editTeamMember(@PathVariable("id") TeamMemberId teamMemberId,
                                 @Validated(EditTeamMemberValidationGroupSequence.class)
                                   @ModelAttribute("teamMember") EditTeamMemberFormData formData,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", List.of(Gender.MALE, Gender.FEMALE, Gender.OTHER));
            model.addAttribute("editMode", EditMode.UPDATE);
            return "team/edit";
        }
        teamMemberService.editTeamMember(teamMemberId, formData.toParameters());
        return "redirect:/team";
    }
}
