package ua.edu.chnu.kkn.demo.team;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({Default.class, TeamMemberValidationGroupOne.class, TeamMemberValidationGroupTwo.class})
public interface CreateTeamMemberValidationGroupSequence {
}
