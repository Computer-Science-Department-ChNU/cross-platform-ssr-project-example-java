package ua.edu.chnu.kkn.demo.team.teammember;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
                parameters.getTeamMemberName(),
                parameters.getGender(),
                parameters.getBirthday(),
                parameters.getEmail(),
                parameters.getPhoneNumber()
        );
        return teamMemberRepository.save(teamMember);
    }

    @Override
    public Optional<TeamMember> getTeamMember(TeamMemberId teamMemberId) {
        return teamMemberRepository.findById(teamMemberId);
    }

    @Override
    public Page<TeamMember> getTeamMembers(Pageable pageable) {
        return teamMemberRepository.findAll(pageable);
    }

    @Override
    public boolean teamMemberWithEmailExists(Email email) {
        return teamMemberRepository.existsByEmail(email);
    }

    @Override
    public TeamMember editTeamMember(TeamMemberId teamMemberId, EditTeamMemberParameters teamMemberParameters) {
        var teamMember = teamMemberRepository
                .findById(teamMemberId)
                .orElseThrow(() -> new TeamMemberNotFoundException(teamMemberId));
        if (teamMemberParameters.getVersion() != teamMember.getVersion()) {
            throw new ObjectOptimisticLockingFailureException(TeamMember.class, teamMember.getId().asString());
        }
        teamMemberParameters.update(teamMember);
        return teamMember;
    }

    @Override
    public void deleteTeamMember(TeamMemberId teamMemberId) {
        teamMemberRepository.deleteById(teamMemberId);
    }
}
