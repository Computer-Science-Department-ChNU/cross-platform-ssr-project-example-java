package ua.edu.chnu.kkn.demo.team.teammember;

import java.time.LocalDate;

public class EditTeamMemberParameters extends CreateTeamMemberParameters {

    private final long version;

    public EditTeamMemberParameters(long version, TeamMemberName teamMemberName,
                                    Gender gender, LocalDate birthday, Email email,
                                    PhoneNumber phoneNumber) {
        super(teamMemberName, gender, birthday, email, phoneNumber);
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void update(TeamMember teamMember) {
        teamMember.setTeamMemberName(getTeamMemberName());
        teamMember.setGender(getGender());
        teamMember.setBirthday(getBirthday());
        teamMember.setEmail(getEmail());
        teamMember.setPhoneNumber(getPhoneNumber());
    }
}
