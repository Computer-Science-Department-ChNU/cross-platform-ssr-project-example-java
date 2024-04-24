package ua.edu.chnu.kkn.demo.team.teammember;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tt_team_member")
public class TeamMember extends AbstractEntity<TeamMemberId> {

    /**
     * Default constructor for JPA
     */
    protected TeamMember() {
    }

    public TeamMember(TeamMemberId id) {
        super(id);
    }
}