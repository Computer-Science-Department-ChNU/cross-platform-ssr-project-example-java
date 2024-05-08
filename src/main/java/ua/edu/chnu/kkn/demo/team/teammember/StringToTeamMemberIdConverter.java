package ua.edu.chnu.kkn.demo.team.teammember;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StringToTeamMemberIdConverter implements Converter<String, TeamMemberId> {

    @Override
    public TeamMemberId convert(@NotNull String source) {
        return new TeamMemberId(UUID.fromString(source));
    }
}
