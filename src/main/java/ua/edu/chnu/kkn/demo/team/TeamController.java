package ua.edu.chnu.kkn.demo.team;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.chnu.kkn.demo.team.teammember.TeamMemberService;

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
}
