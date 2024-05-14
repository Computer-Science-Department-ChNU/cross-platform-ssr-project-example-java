package ua.edu.chnu.kkn.demo.team.teammember;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TeamMemberService {

    TeamMember createTeamMember(CreateTeamMemberParameters parameters);

    Optional<TeamMember> getTeamMember(TeamMemberId teamMemberId);

    Page<TeamMember> getTeamMembers(Pageable pageable);

    boolean teamMemberWithEmailExists(Email email);

    TeamMember editTeamMember(TeamMemberId teamMemberId, EditTeamMemberParameters teamMemberParameters);

    void deleteTeamMember(TeamMemberId teamMemberId);
}
