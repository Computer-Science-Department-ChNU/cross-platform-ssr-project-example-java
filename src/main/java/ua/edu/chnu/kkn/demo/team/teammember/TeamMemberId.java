package ua.edu.chnu.kkn.demo.team.teammember;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class TeamMemberId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected TeamMemberId() {
   }

   public TeamMemberId(UUID id) {
       super(id);
   }
}