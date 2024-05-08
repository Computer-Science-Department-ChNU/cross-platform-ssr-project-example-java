package ua.edu.chnu.kkn.demo.team;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import ua.edu.chnu.kkn.demo.team.teammember.CreateTeamMemberParameters;
import ua.edu.chnu.kkn.demo.team.teammember.Gender;
import ua.edu.chnu.kkn.demo.team.teammember.PhoneNumber;
import ua.edu.chnu.kkn.demo.team.teammember.TeamMemberName;

import java.time.LocalDate;

@NotExistingTeamMember(groups = TeamMemberValidationGroupTwo.class)
public class CreateTeamMemberFormData {

    @NotBlank
    @Size(min = 2, max = 200, groups = TeamMemberValidationGroupOne.class)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 200, groups = TeamMemberValidationGroupOne.class)
    private String lastName;
    @NotNull
    private Gender gender;
    @NotBlank
    @Email(groups = TeamMemberValidationGroupOne.class)
    private String email;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    @NotBlank
    @Pattern(regexp = "[0-9.\\-() x/+]+", groups = TeamMemberValidationGroupOne.class)
    private String phoneNumber;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public CreateTeamMemberParameters toParameters() {
        return new CreateTeamMemberParameters(new TeamMemberName(firstName, lastName),
                gender,
                birthday,
                new ua.edu.chnu.kkn.demo.team.teammember.Email(email),
                new PhoneNumber(phoneNumber));
    }
}
