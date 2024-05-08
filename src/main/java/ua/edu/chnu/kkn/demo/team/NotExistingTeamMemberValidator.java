package ua.edu.chnu.kkn.demo.team;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import ua.edu.chnu.kkn.demo.team.teammember.Email;
import ua.edu.chnu.kkn.demo.team.teammember.TeamMemberService;

public class NotExistingTeamMemberValidator
        implements ConstraintValidator<NotExistingTeamMember, CreateTeamMemberFormData> {

    private final TeamMemberService service;

    @Autowired
    public NotExistingTeamMemberValidator(TeamMemberService service) {
        this.service = service;
    }

    @Override
    public void initialize(NotExistingTeamMember constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(CreateTeamMemberFormData formData, ConstraintValidatorContext context) {
        if (service.teamMemberWithEmailExists(new Email(formData.getEmail()))) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{TeamMemberAlreadyExisting}")
                    .addPropertyNode("email").addConstraintViolation();
            return false;
        }
        return true;
    }
}
