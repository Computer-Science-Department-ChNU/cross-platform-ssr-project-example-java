package ua.edu.chnu.kkn.demo.team.teammember;

import java.time.LocalDate;

public record CreateTeamMemberParameters(
        TeamMemberName teamMemberName,
        Gender gender,
        LocalDate birthday,
        Email email,
        PhoneNumber phoneNumber
) {

}
