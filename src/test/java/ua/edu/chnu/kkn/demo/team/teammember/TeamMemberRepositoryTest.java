package ua.edu.chnu.kkn.demo.team.teammember;

import io.github.wimdeblauwe.jpearl.InMemoryUniqueIdGenerator;
import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.test.context.ActiveProfiles;
import ua.edu.chnu.kkn.demo.team.teammember.TeamMember;
import ua.edu.chnu.kkn.demo.team.teammember.TeamMemberId;
import ua.edu.chnu.kkn.demo.team.teammember.TeamMemberRepository;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("data-jpa-test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeamMemberRepositoryTest {
    private final TeamMemberRepository repository;
    private final JdbcTemplate jdbcTemplate;
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    TeamMemberRepositoryTest(TeamMemberRepository repository,
                             JdbcTemplate jdbcTemplate) {
        this.repository = repository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @BeforeEach
    void validatePreconditions() {
        assertThat(repository.count()).isZero();
    }

    @Test
    void testSaveTeamMember() {
        TeamMemberId id = repository.nextId();
        repository.save(new TeamMember(id,
                new TeamMemberName("Tommy", "Walton"),
                Gender.MALE,
                LocalDate.of(2001, Month.FEBRUARY, 17),
                new Email("tommy.walton@gmail.com"),
                new PhoneNumber("202 555 0192")));
        entityManager.flush();
        assertThat(jdbcTemplate.queryForObject("SELECT id FROM tt_team_member", UUID.class)).isEqualTo(id.getId());
        assertThat(jdbcTemplate.queryForObject("SELECT first_name FROM tt_team_member", String.class)).isEqualTo("Tommy");
        assertThat(jdbcTemplate.queryForObject("SELECT last_name FROM tt_team_member", String.class)).isEqualTo("Walton");
        assertThat(jdbcTemplate.queryForObject("SELECT gender FROM tt_team_member", Gender.class)).isEqualTo(Gender.MALE);
        assertThat(jdbcTemplate.queryForObject("SELECT birthday FROM tt_team_member", LocalDate.class)).isEqualTo("2001-02-17");
        assertThat(jdbcTemplate.queryForObject("SELECT email FROM tt_team_member", String.class)).isEqualTo("tommy.walton@gmail.com");
        assertThat(jdbcTemplate.queryForObject("SELECT phone_number FROM tt_team_member", String.class)).isEqualTo("202 555 0192");
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public UniqueIdGenerator<UUID> uniqueIdGenerator() {
            return new InMemoryUniqueIdGenerator();
        }
    }
}