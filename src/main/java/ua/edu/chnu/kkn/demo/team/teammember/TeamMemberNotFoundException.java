package ua.edu.chnu.kkn.demo.team.teammember;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamMemberNotFoundException extends RuntimeException {

    public TeamMemberNotFoundException(TeamMemberId teamMemberId) {
        super(String.format("TeamMember with id %s not found", teamMemberId.asString()));
    }
}
