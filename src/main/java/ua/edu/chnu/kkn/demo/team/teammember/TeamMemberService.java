package ua.edu.chnu.kkn.demo.team.teammember;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamMemberService {

    TeamMember createUser(CreateTeamMemberParameters parameters);

    Page<TeamMember> getUsers(Pageable pageable);
}
