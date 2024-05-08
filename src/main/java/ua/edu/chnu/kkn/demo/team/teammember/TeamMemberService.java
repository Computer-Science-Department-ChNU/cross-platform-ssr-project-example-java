package ua.edu.chnu.kkn.demo.team.teammember;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamMemberService {

    TeamMember createTeamMember(CreateTeamMemberParameters parameters);

    Page<TeamMember> getTeamMembers(Pageable pageable);

    boolean teamMemberWithEmailExists(Email email);
}
