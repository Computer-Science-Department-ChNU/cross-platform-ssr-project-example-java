package ua.edu.chnu.kkn.demo.team.teammember;

import jakarta.persistence.Embeddable;
import org.springframework.util.Assert;
import org.testcontainers.shaded.com.google.common.base.MoreObjects;

import java.util.Objects;

@Embeddable
public class TeamMemberName {
    private String firstName;
    private String lastName;

    protected TeamMemberName() {
    }

    public TeamMemberName(String firstName, String lastName) {
        Assert.hasText(firstName, "firstName cannot be blank");
        Assert.hasText(lastName, "lastName cannot be blank");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TeamMemberName userName = (TeamMemberName) o;
        return Objects.equals(firstName, userName.firstName) &&
                Objects.equals(lastName, userName.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .toString();
    }
}
