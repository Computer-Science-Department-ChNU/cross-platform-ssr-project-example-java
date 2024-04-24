package ua.edu.chnu.kkn.demo.teammember;

import io.github.wimdeblauwe.jpearl.UniqueIdGenerator;

import java.util.UUID;

public class TeamMemberRepositoryImpl implements TeamMemberRepositoryCustom {
    private final UniqueIdGenerator<UUID> generator;

    public TeamMemberRepositoryImpl(UniqueIdGenerator<UUID> generator) {
        this.generator = generator;
    }

    @Override
    public TeamMemberId nextId() {
        return new TeamMemberId(generator.getNextUniqueId());
    }
}