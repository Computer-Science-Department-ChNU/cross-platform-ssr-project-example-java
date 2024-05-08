package ua.edu.chnu.kkn.demo.team.teammember;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface TeamMemberRepository extends CrudRepository<TeamMember, TeamMemberId>,
        PagingAndSortingRepository<TeamMember, TeamMemberId>,
        TeamMemberRepositoryCustom {

    boolean existsByEmail(Email email);
}