package ua.edu.chnu.kkn.demo.teammember;

import io.github.wimdeblauwe.jpearl.AbstractEntity;

import jakarta.persistence.Entity;

@Entity
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