package ua.edu.chnu.kkn.demo;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ua.edu.chnu.kkn.demo.team.teammember.*;

import java.time.LocalDate;
import java.time.ZoneId;

@Component
@Profile("init-db")
public class DatabaseInitializer implements CommandLineRunner {

    private final Faker faker = new Faker();

    private final TeamMemberService teamMemberService;

    public DatabaseInitializer(TeamMemberService teamMemberService) {
        this.teamMemberService = teamMemberService;
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < 20; i++) {
            teamMemberService.createUser(newRandomTeamMemberParameters());
        }
    }

    private CreateTeamMemberParameters newRandomTeamMemberParameters() {
        Name name = faker.name();
        TeamMemberName teamMemberName = new TeamMemberName(name.firstName(), name.lastName());
        Gender gender = faker.bool().bool() ? Gender.MALE : Gender.FEMALE;
        LocalDate birthday = LocalDate.ofInstant(faker.date().birthday(10, 40).toInstant(), ZoneId.systemDefault());
        Email email = new Email(faker.internet().emailAddress(generateEmailLocalPart(teamMemberName)));
        PhoneNumber phoneNumber = new PhoneNumber(faker.phoneNumber().phoneNumber());
        return new CreateTeamMemberParameters(teamMemberName, gender, birthday, email, phoneNumber);
    }

    private String generateEmailLocalPart(TeamMemberName userName) {
        return String.format("%s.%s",
                StringUtils.remove(userName.getFirstName().toLowerCase(), "'"),
                StringUtils.remove(userName.getLastName().toLowerCase(), "'"));
    }
}
