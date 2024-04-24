package ua.edu.chnu.kkn.demo.team.teammember;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "tt_team_member")
public class TeamMember extends AbstractEntity<TeamMemberId> {

    @NotNull
    private TeamMemberName teamMemberName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    private LocalDate birthday;

    @NotNull
    private Email email;

    @NotNull
    private PhoneNumber phoneNumber;

    /**
     * Default constructor for JPA
     */
    protected TeamMember() {
    }

    public TeamMember(TeamMemberId id,
                      TeamMemberName teamMemberName,
                      Gender gender,
                      LocalDate birthday,
                      Email email,
                      PhoneNumber phoneNumber) {
        super(id);
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
