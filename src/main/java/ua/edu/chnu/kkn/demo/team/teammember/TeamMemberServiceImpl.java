package ua.edu.chnu.kkn.demo.team.teammember;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberServiceImpl(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    @Override
    public TeamMember createTeamMember(CreateTeamMemberParameters parameters) {
        TeamMemberId teamMemberId = teamMemberRepository.nextId();
        var teamMember = new TeamMember(
                teamMemberId,
                parameters.teamMemberName(),
                parameters.gender(),
                parameters.birthday(),
                parameters.email(),
                parameters.phoneNumber()
        );
        return teamMemberRepository.save(teamMember);
    }

    @Override
    public Page<TeamMember> getTeamMembers(Pageable pageable) {
        return teamMemberRepository.findAll(pageable);
    }
}
