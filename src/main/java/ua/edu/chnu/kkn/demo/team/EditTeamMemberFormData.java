package ua.edu.chnu.kkn.demo.team;

import ua.edu.chnu.kkn.demo.team.teammember.*;

public class EditTeamMemberFormData extends CreateTeamMemberFormData {

    private String id;

    private long version;

    public static EditTeamMemberFormData fromTeamMember(TeamMember teamMember) {
        EditTeamMemberFormData result = new EditTeamMemberFormData();
        result.setId(teamMember.getId().asString());
        result.setVersion(teamMember.getVersion());
        result.setFirstName(teamMember.getTeamMemberName().getFirstName());
        result.setLastName(teamMember.getTeamMemberName().getLastName());
        result.setGender(teamMember.getGender());
        result.setBirthday(teamMember.getBirthday());
        result.setEmail(teamMember.getEmail().asString());
        result.setPhoneNumber(teamMember.getPhoneNumber().asString());
        return result;
    }

    public EditTeamMemberParameters toParameters() {
        return new EditTeamMemberParameters(
                version, new TeamMemberName(getFirstName(),
                getLastName()), getGender(), getBirthday(),
                new Email(getEmail()), new PhoneNumber(getPhoneNumber())
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
