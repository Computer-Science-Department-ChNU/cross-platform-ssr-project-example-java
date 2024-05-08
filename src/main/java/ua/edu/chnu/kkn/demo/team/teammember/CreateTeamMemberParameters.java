package ua.edu.chnu.kkn.demo.team.teammember;

import java.time.LocalDate;

public class CreateTeamMemberParameters {

    private final TeamMemberName teamMemberName;
    private final Gender gender;
    private final LocalDate birthday;
    private final Email email;
    private final PhoneNumber phoneNumber;

    public CreateTeamMemberParameters(TeamMemberName teamMemberName, Gender gender, LocalDate birthday, Email email, PhoneNumber phoneNumber) {
        this.teamMemberName = teamMemberName;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public TeamMemberName getTeamMemberName() {
        return teamMemberName;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Email getEmail() {
        return email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }
}
